package day0324.kr.co.sist.pstmt.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import day0324.kr.co.sist.pstmt.design.PreparedWindow;
import day0324.kr.co.sist.pstmt.service.PreparedService;
import day0324.kr.co.sist.pstmt.vo.PstmtMemberVO;

@SuppressWarnings("all")
public class PreparedWindowEvent extends WindowAdapter implements ActionListener, MouseListener {


	private PreparedWindow pw;
	private JButton jbtnAdd;
	private JButton jbtnChange;
	private JButton jbtnDelete;
	private JButton jbtnClose;
	private PreparedService ps;
	
	private int selectedNum;

	public PreparedWindowEvent(PreparedWindow pw) {
		selectedNum=-1;	//선택되지 않아서 default 값 -1
		
		this.pw = pw;
		jbtnAdd = pw.getJbtnAdd();
		jbtnChange = pw.getJbtnChange();
		jbtnDelete = pw.getJbtnDelete();
		jbtnClose = pw.getJbtnClose();
		
		
		// Total JLabel 값 바꾸기
		ps=new PreparedService();
		changeList(); //리스트 갱신
		pw.getJlblCount2().setText(String.valueOf(ps.searchAllCnt()));
		
	} // ExamWindowEvent
	
	@Override
	public void mouseClicked(MouseEvent e) {
		boolean flag=false;
		flag=JOptionPane.showConfirmDialog(
				pw, "새 창에서 결과를 확인하시겠습니까?") 
				== JOptionPane.OK_OPTION;
		
//		선택한 아이템에서 처음 , 값까지를 잘라서 정수로 변환(회원번호)
		selectedNum=Integer.parseInt( pw.getJlData().getSelectedValue().split(",")[0] );
		if(flag) {
			
			StringBuilder output=new StringBuilder();
			output
			.append(selectedNum).append("회원 검색 결과\n");
			
			PreparedService ps=new PreparedService();
			PstmtMemberVO pmVO=ps.searchOneMember(selectedNum);
			
//			번호로 검색된 레코드가 존재하지 않을 때
			if( pmVO==null ) {
				output
				.append("존재하지 않는 회원입니다.")
				;
			} else {
				int nowYear=Calendar.getInstance().get(Calendar.YEAR);
				SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
				
				output
				.append("회원명 : ").append(pmVO.getName()).append("\n")
				.append("나이 : ").append(pmVO.getAge()).append("\n")
				.append("태어난 해 : ").append(nowYear-pmVO.getAge()+1).append("\n")
				.append("성별 : " ).append(pmVO.getGender()).append("\n")
				.append("전화번호 : " ).append(pmVO.getTel()).append("\n")
				.append("입력일 : " )
				.append( sdf.format(pmVO.getInputdate()) ).append("(")
				.append( pmVO.getStrInputDate() ).append(")")
				;
				
				JTextArea jta=new JTextArea(output.toString(), 8, 80);
				JScrollPane jsp=new JScrollPane(jta);
				JOptionPane.showMessageDialog(pw, jsp);
			} //end else if
		} //end if

//		클릭한 아이템의 값을 DLM에서 얻어와서 배열로 저장
		String[] clickDataArr = pw.getJlData().getSelectedValue().split(",");
		
		System.out.println("-------------clickDataArr--------------"+clickDataArr.length);
		if(clickDataArr.length != 7) {
			return;		//early return 
		} //end if
		
//		선택한 번호를 인스턴스 변수에 설정한다.
		selectedNum=Integer.parseInt(clickDataArr[0]);

		// '입력' 패널창에 차근차근 데이터 담자.
		pw.getJtfName().setText(clickDataArr[1]);
		pw.getJtfAge().setText(clickDataArr[2]);
		pw.getJtfPhoneNumber().setText(clickDataArr[4]);

		// 선택된 값과 라디오버튼의 라벨을 비교
		JRadioButton jrbTemp=pw.getJrbWoman();
		if (clickDataArr[3].equals(pw.getJrbMan().getText())) {
			jrbTemp=pw.getJrbMan();
		} //end if
		
		jrbTemp.setSelected(true);

	} //mouseClicked

	/******************** 사용X ************************/
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/******************** 사용X ************************/


	public void windowClosing() {
		pw.dispose();
	} // windowClosing

	public void addMember(PstmtMemberVO pmVO) {
		// 성별 선택 안되어있으면 Early Return
		if(pmVO.getGender().isEmpty()) {
			JOptionPane.showMessageDialog(pw, "성별은 필수 선택입니다.");
			return;
		} // end if

//		업무로직을 처리
		String msg=pmVO.getName()+"님의 데이터를 추가하지 못하였습니다.";
		if(ps.addPstmtMember(pmVO)){ 
			msg=pmVO.getName()+"님의 데이터를 추가하였습니다.";
		} //end if
		
		JOptionPane.showMessageDialog(pw, msg);
		
//		입력값 초기화 셋팅
		inputFieldReset();

		// Total JLabel 값 바꾸기
//		ew.getJlblCount2().setText(String.valueOf(ew.getDlm().size()));
		pw.getJlblCount2().setText(String.valueOf(ps.searchAllCnt()));
	}// addList
	
	private void inputFieldReset() {
//		레코드 추가 후 입력 필드를 초기화
		pw.getJtfName().setText("");
		pw.getJtfAge().setText("");
		pw.getJtfPhoneNumber().setText("");
		
		pw.getJtfName().requestFocus();
		
		changeList(); //리스트 갱신
	} //inputFieldReset

	private boolean numValidate() {
		boolean flag=false;
		
		if(flag=(selectedNum == -1)) {
			JOptionPane.showMessageDialog(pw, "리스트에서 아이템을 클릭해주세요.");
		} //end if
		
		return flag;
	} //numValidate
	
	public void modifyMember(PstmtMemberVO pmVO) {
		
		if(numValidate()) {
			return;
		} //end if
		
//		업무로직을 처리
		pmVO.setNum(selectedNum);
		
		String alertMsg="레코드 변경 실패";
		if(ps.modifyPstmtMember(pmVO)) {	//변경을 수행
			alertMsg="회원정보가 성공적으로 변경되었습니다.";
		} //end if
		
		JOptionPane.showMessageDialog(pw, alertMsg);
		
//		레코드 변경 후 입력 필드를 초기화
		inputFieldReset();
		
		selectedNum=-1;
		
	} //modifyMember
	
	public void removeMember() {
		
		if(numValidate()) {
			return;		//early return
		} //end if
		
		String alterMsg="회원 정보를 삭제하지 못헀습니다.";
		
		if(ps.removeStmtMember(selectedNum)) {
			alterMsg="회원 정보를 삭제하였습니다.";
		} //end if
		
		JOptionPane.showMessageDialog(pw, alterMsg);
		
//		레코드 변경 후 입력 필드를 초기화
		inputFieldReset();
		
		// Total JLabel 값 바꾸기
//		ew.getJlblCount2().setText(String.valueOf(ew.getDlm().size()));
		pw.getJlblCount2().setText(String.valueOf(ps.searchAllCnt()));
		
		selectedNum = -1;
	} // removeMember

	public void changeList() {
//		DBMS에서 모든 회원정보를 검색하여
		List<PstmtMemberVO> list=ps.searchAllMember();
//		JList에 추가(MVC Pattern 개발된 class)
		StringBuilder modelData=new StringBuilder(); 
//		모델에 값을 추가하기 전에 모든 데이터를 초기화
		pw.getDlm().removeAllElements();
//		레코드가 존재하지 않을 때
		if(list.isEmpty()) {
			pw.getDlm().addElement("회원 정보가 존재하지 않습니다.");
		} //end if
		
//		레코드가 존재할 때
		SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy EEEE");
		for(PstmtMemberVO pmVO : list) {
			modelData.delete(0, modelData.length());
			modelData
			.append(pmVO.getNum()).append(",")
			.append(pmVO.getName()).append(",")
			.append(pmVO.getAge()).append(",")
			.append(pmVO.getGender()).append(",")
			.append(pmVO.getTel()).append(",")
//			rs.getDate() 사용하면 날짜형식을 다르게 보여줄 수 있음.
			.append( sdf.format( pmVO.getInputdate() ) ).append(",")
//			to_char 사용하면 동일한 형식으로만 날짜를 보여줌.
			.append(pmVO.getStrInputDate());
			
			pw.getDlm().addElement(modelData.toString());
		} //end for
		
	}// changeList

	@Override
	public void windowClosing(WindowEvent e) {
		windowClosing();
	}// windowClosing

	@Override
	public void actionPerformed(ActionEvent e) {
		// 자주 쓰는 것들 변수에 담아보자
		String name = pw.getJtfName().getText();
		String age = pw.getJtfAge().getText();
		String phoneNumber = pw.getJtfPhoneNumber().getText();
		String gender = pw.getJrbMan().getText();


		// 버튼 별로 다중 이벤트 처리
		if (e.getSource() == jbtnClose) {
			windowClosing();
		} // 종료버튼
		
		if (e.getSource() == jbtnAdd) {
			// 라디오버튼 선택값 String 변수 만들기
			if (pw.getJrbMan().isSelected() == true) {
				gender = pw.getJrbMan().getText();
			} // end if
			if (pw.getJrbWoman().isSelected() == true) {
				gender = pw.getJrbWoman().getText();
			} // end if

			try {
//				입력된 값을 VO객체에 할당
				PstmtMemberVO pmVO=new PstmtMemberVO(0, name,
						Integer.parseInt(age), gender, phoneNumber, null, null);
//				업무로직을 처리하고 추가
				addMember(pmVO);	//업무로직을 처리
				
			} catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(pw, "나이는 숫자입니다.");
			} //end try catch
			
		} // 추가버튼
		
		if (e.getSource() == jbtnChange) {
			
//			changeList(name, age, phoneNumber, gender);
			
			try {
//				입력된 값을 VO객체에 할당
				PstmtMemberVO pmVO=new PstmtMemberVO(0, name,
						Integer.parseInt(age), gender, phoneNumber, null, null);
//				업무로직을 처리하고 추가
				modifyMember(pmVO);	//업무로직을 처리
				
			} catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(pw, "나이는 숫자입니다.");
			} //end try catch
		} // 변경버튼
		
		if (e.getSource() == jbtnDelete) {
//			deleteList(name);
			removeMember();
		} // 삭제버튼

	}// actionPerformed

}// class
