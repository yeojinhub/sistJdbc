package day0327;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SearchZipcodeViewEvt extends MouseAdapter implements ActionListener {
	
	private SearchZipcodeView szv;
	
	public SearchZipcodeViewEvt(SearchZipcodeView szv) {
		this.szv=szv;
	} //SearchZipcodeViewEvt

	@Override
	public void mouseClicked(MouseEvent me) {
		sendZipcode();
	} //mouseClicked

	@Override
	public void actionPerformed(ActionEvent ae) {
		searchZipCode();
	} //actionPerformed
	
	public void sendZipcode() {
		int selectedBtn=JOptionPane.showConfirmDialog(szv, "우편번호를 사용하시겠습니까?");
		
		switch( selectedBtn ) {
		case JOptionPane.OK_OPTION:
			JTable table=szv.getTable();
//			JTable에 선택된 행의 값을 얻기
			System.out.println( "선택한 행의 번호 :" + table.getSelectedRow()
			+ ", 열의 번호 : " + table.getSelectedColumn() );
			
			int selectedRow = table.getSelectedRow();
			MemberView mv=szv.getMv();
			
			mv.getJtfZipcode().setText( (String)table.getValueAt(selectedRow, 0) );
			mv.getJtfAddress().setText( table.getValueAt(selectedRow, 1).toString() );
			mv.getJtfDetails().requestFocus();
//			자식 창 닫기
			szv.dispose();
			
//			int colmnCnt=table.getSelectedColumnCount();
//			int selectedCol = table.getSelectedColumn();
//			
//			System.out.println( table.getSelectedColumnCount() );
//			System.out.println( table.getValueAt(selectedRow, selectedCol) );
//			
//			for(int col=0; col<=colmnCnt; col++) {
//				System.out.println( table.getValueAt(selectedRow, col) );
//			} //end for
		} //end switch
	} //sendZipcode
	
	public void searchZipCode() {
		String dong=szv.getJtfDongName().getText().trim();
		if(dong.isEmpty() ) {
			JOptionPane.showMessageDialog(szv, "동 이름은 필수 입력입니다.");
//			early return
			return;
		} //end if
		
		SearchZipcodeService szs=new SearchZipcodeService();
		List<ZipcodeVO> list=szs.searchzipZipcode(dong);
		
//		조회결과를 JTable 에 추가.
//		1.조회결과로 배열을 만들고,
		String[] data=null;
		Iterator<ZipcodeVO> ita=list.iterator();
		
		ZipcodeVO zVO=null;
		StringBuilder addr=new StringBuilder();
		
//		모델을 받기.
		DefaultTableModel dtm=szv.getDtm();
		
		if( list.isEmpty() ) {
			JOptionPane.showMessageDialog(szv, dong+"은 존재하지 않습니다.");
			szv.getJtfDongName().setText("");
//			early return
			return;
		} //end if
		
//		기존에 존재하는 데이터를 삭제하고,
		if( dtm.getRowCount() >0 ) {
//			행수를 초기화.
			dtm.setRowCount(0);
		} //end if
		
//		새로운 데이터를 채움.
		while( ita.hasNext() ) {
			zVO=ita.next();
			addr.delete(0, addr.length() );
			
			addr
			.append( zVO.getSido() ).append(" ")
			.append( zVO.getGugun() ).append(" ")
			.append( zVO.getDong() ).append(" ")
			.append( zVO.getBunji() )
			;
			
//			우편번호, 주소
			data=new String[2];
			data[0]=zVO.getZipcode();
			data[1]=addr.toString();
			
//			2.DefaultTableMode 에 추가(addRow)
			dtm.addRow(data);
		} //end while
		
		szv.getJtfDongName().setText("");
		
	} //searchZipCode

} //class
