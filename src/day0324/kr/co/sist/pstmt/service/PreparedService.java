package day0324.kr.co.sist.pstmt.service;

import java.sql.SQLException;
import java.util.List;

import day0324.kr.co.sist.pstmt.dao.PreparedDAO;
import day0324.kr.co.sist.pstmt.vo.PstmtMemberVO;

/**
 * BL(Business Logic)을 구현하기 위한 클래스.
 */
public class PreparedService {
	
	
	public PreparedService() {
		
	} //StatementService
	
	/**
	 * 업무로직 : 나이는 20~30대만 입력, 만약 해당 나이가 아니면 20으로 설정
	 * @param pmVO
	 */
	public boolean addPstmtMember(PstmtMemberVO pmVO) {
		boolean flag=false;
		PreparedDAO pDAO=PreparedDAO.getInstance();
		
		try {
			pDAO.insertPstmtMember(pmVO);
			flag=true;
		} catch (SQLException se) {
			se.printStackTrace();
		} //end try catch
		
		return flag;
	} //addPstmtMember
	
	public boolean modifyPstmtMember(PstmtMemberVO pmVO) {
		boolean flag=false;
		
		PreparedDAO pDAO=PreparedDAO.getInstance();
		try {
			pDAO.updatePstmtMember(pmVO);
		} catch (SQLException se) {
			se.printStackTrace();
		} //end try catch
		
		return flag;
	} //modifyPstmtMember
	
	public boolean removeStmtMember(int num) {
		boolean flag=false;
		
		PreparedDAO pDAO=PreparedDAO.getInstance();
		
		try {
			flag=pDAO.deletePstmtMember(num)==1;
		} catch (SQLException se) {
			se.printStackTrace();
		} //end try catch
		
		return flag;
	} //revoveStmtMember
	
	public int searchAllCnt() {
		int cnt=0;
		
		PreparedDAO pDAO=PreparedDAO.getInstance();
		
		try {
			cnt=pDAO.selectPstmtCntMember();
		} catch (SQLException se) {
			se.printStackTrace();
		} //end try catch
		
		return cnt;
	} //searchAllCnt
	
	public List<PstmtMemberVO> searchAllMember() {
		List<PstmtMemberVO> list=null;
		
		PreparedDAO pDAO=PreparedDAO.getInstance();
		
		try {
			list=pDAO.selectAllMember();
		} catch (SQLException se) {
			se.printStackTrace();
		} //end try catch
		
		return list;
	}  //searchAllMember
	
	public PstmtMemberVO searchOneMember(int num) {
		PstmtMemberVO pmVO=null;
		
		PreparedDAO pDAO=PreparedDAO.getInstance();
		
		try {
			pmVO=pDAO.selectOneMember(num);
		} catch (SQLException se) {
			se.printStackTrace();
		} //end try catch
		
		return pmVO;
	} //searchOneMember
	
} //class
