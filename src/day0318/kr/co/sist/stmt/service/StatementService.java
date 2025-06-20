package day0318.kr.co.sist.stmt.service;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import day0318.kr.co.sist.stmt.dao.StatementDAO;
import day0318.kr.co.sist.stmt.design.StatementWindow;
import day0318.kr.co.sist.stmt.vo.StatementMemberVO;

/**
 * BL(Business Logic)을 구현하기 위한 클래스.
 */
public class StatementService {
	
	private StatementWindow sw;
	
	public StatementService(StatementWindow sw) {
		this.sw=sw;
	} //StatementService
	
	/**
	 * 업무로직 : 나이는 20~30대만 입력, 만약 해당 나이가 아니면 20으로 설정
	 * @param smVO
	 */
	public void addStmtMember(StatementMemberVO smVO) {
		StatementDAO sDAO=new StatementDAO();
		
//		업무로직 처리
//		if(!(smVO.getAge()>19 && smVO.getAge() < 40)) {
//			smVO.setAge(20);
//		} //end if
		
		StringBuilder resultMsg= new StringBuilder();
		
		try {
			sDAO.insertStmtMember(smVO);
			resultMsg.append(smVO.getName())
			.append("님의 정보를 추가 성공하였습니다.");
		} catch (SQLException se) {
			switch(se.getErrorCode()) {
				case 1400:
					resultMsg.
					append("이름은 필수입력입니다.");
					break;
				case 1438:
					resultMsg.
					append("나이는 0~999까지만 입력가능합니다.");
					break;
				case 12899:
					resultMsg.
					append("이름은 한글 10자, 영어는 30자, 전화번호는 '-'포함 13자입니다.");
					break;
			} //end switch
			se.printStackTrace();
		} finally{
			JOptionPane.showMessageDialog(sw, resultMsg.toString());
		} //end try catch finally
		
	} //addStmtMember
	
	public boolean modifyStmtMember(StatementMemberVO smVO) {
		boolean flag=false;
		
		StatementDAO sDAO=new StatementDAO();
		
		try {
			flag=sDAO.updateStmtMember(smVO)!=0;
		} catch (SQLException se) {
			se.printStackTrace();
		} //end try catch
		
		return flag;
	} //modifyStmtMember
	
	public boolean removeStmtMember(int num) {
		boolean flag=false;
		
		StatementDAO sDAO=new StatementDAO();
		
		try {
			flag=sDAO.deleteStmtMember(num)!=0;
		} catch (SQLException se) {
			se.printStackTrace();
		} //end try catch
		
		return flag;
	} //revoveStmtMember
	
	public int searchAllCnt() {
		int cnt=0;
		
		StatementDAO sDAO=new StatementDAO();
		cnt=sDAO.selectCntStmtMember();
		
		return cnt;
	} //searchAllCnt
	
	public List<StatementMemberVO> searchAllMember() {
		List<StatementMemberVO> list=null;
		
		StatementDAO sDAO=new StatementDAO();
		
		try {
			list=sDAO.selectAllStmtMember();
		} catch (SQLException se) {
			se.printStackTrace();
		} //end try catch
		
		return list;
	}  //searchAllMember
	
} //class
