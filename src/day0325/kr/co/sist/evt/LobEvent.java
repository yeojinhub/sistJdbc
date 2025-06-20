package day0325.kr.co.sist.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import day0325.kr.co.sist.service.LobService;
import day0325.kr.co.sist.view.LobView;
import day0325.kr.co.sist.vo.LobVO;

public class LobEvent extends WindowAdapter implements ActionListener {
	private LobView lv;
	private File addFile;

	public LobEvent(LobView lv) {
		this.lv = lv;
	}// LobEvent

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == lv.getJbtnAdd()) {
			addMember();
		} // end if

		if (ae.getSource() == lv.getJbtnExit()) {
			closeWindow();
		} // end if

		if (ae.getSource() == lv.getJbtnSerch()) {
			searchMember();
		} // end if

		if (ae.getSource() == lv.getJbtnSelect()) {
			uploadImage();
		} // end if
	}// actionPerformed

	private void uploadImage() {
		JFileChooser jfc=new JFileChooser();
		jfc.showOpenDialog(lv);
		
		addFile=jfc.getSelectedFile();
		if( !addFile.exists() ) {
			JOptionPane.showMessageDialog(lv, "파일이 존재하지 않습니다.");
//			early return
			return;
		} //end if
		
		ImageIcon preview=new ImageIcon( addFile.getAbsolutePath() );
		lv.getJlblImage().setIcon(preview);
		
		System.out.println("uploadImage");
	}// uploadImage

	private void addMember() {
		
		LobVO lVO=new LobVO();
		lVO.setName(lv.getJtfName().getText().trim());
		lVO.setEmail(lv.getJtfEmail().getText().trim());
		lVO.setImg_name(addFile!=null && addFile.exists() ? addFile.getAbsolutePath(): "");
		lVO.setTel(lv.getJtfTel().getText().trim());
		lVO.setIntro(lv.getJtaIntroduction().getText());
		
		LobService ls=new LobService();
		String msg="회원정보가 추가되지 않았습니다.";
		if( ls.addMember(lVO) ) {
			msg="회원정보가 추가되었습니다.";
		} //end if
		
//		입력 폼 초기화
		lv.getJtfName().setText("");
		lv.getJtfNum().setText("");
		lv.getJtfTel().setText("");
		lv.getJtfEmail().setText("");
		addFile=new File("");
		lv.getJlblImage().setIcon( new ImageIcon("C:/dev/workspace/jdbc_lob_prj/src/day0325/kr/co/sist/view/default_img.png") );
		
		JOptionPane.showMessageDialog(lv, msg);
		
		System.out.println("addMember");

	}// addMember

	private void searchMember() {
		LobService ls=new LobService();
		try {
			int num= Integer.parseInt( lv.getJtfNum().getText().trim() );
			LobVO lVO=ls.searchMember(num);
			if(lVO==null) {
				JOptionPane.showMessageDialog(lv, num+"번 회원의 정보는 존재하지 않습니다.");
//				early return
				return;
			} //end if
			
//			DBMS 테이블에서 검색된 결과를 View 에 뿌림.
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			lv.setTitle( "입력일 "+ sdf.format( lVO.getInput_date() ) );
			
			lv.getJtfName().setText( lVO.getName() );
			lv.getJtfEmail().setText( lVO.getEmail() );
			lv.getJtfTel().setText( lVO.getTel() );
			
			String iconPath="C:/dev/workspace/jdbc_lob_prj/src/day0325/kr/co/sist/view/default_img.png";
			
			if( lVO.getImg_name() != null) {
				File file=new File( "c:/dev/img/" + lVO.getImg_name() );
//				기본(default) 아이콘 경로 저장
				if( file.exists() ) {
//					파일이 존재할때만 절대경로 설정
					iconPath=file.getAbsolutePath();
					System.out.println(iconPath);
				} //end if
			} //end if
			ImageIcon icon=new ImageIcon( iconPath );
			lv.getJlblImage().setIcon(icon);
			
			if( lVO.getIntro() != null) {	
				lv.getJtaIntroduction().setText(lVO.getIntro());
			} //end if
			
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(lv, "번호는 숫자만 가능합니다.");
			nfe.printStackTrace();
		} //end try catch
		
		System.out.println("searchMember");
	}// searchMember

	private void closeWindow() {
		System.out.println("closeWindow");
		lv.dispose();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		closeWindow();
	}// windowClosing

	@Override
	public void windowClosed(WindowEvent e) {
		System.exit(0);
	}// windowClosed

}// class
