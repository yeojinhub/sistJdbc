package day0325.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import day0324.kr.co.sist.pstmt.dao.PreparedDAO;
//import day0324.kr.co.sist.pstmt.service.PreparedService;
import day0324.kr.co.sist.pstmt.vo.PstmtMemberVO;

class PstmtUpdateTest {

	@DisplayName("PstmtMember update 테스트")
	@Test
	void test() {
		PstmtMemberVO pmVO=new PstmtMemberVO(1,
				"이장훈", 30, "남자", "010-0000-0000", null, null);
		
		PreparedDAO pDAO=PreparedDAO.getInstance();
//		assertDoesNotThrow( ()-> pDAO.insertPstmtMember(pmVO) );
		try {
			assertEquals(pDAO.updatePstmtMember(pmVO), 1);
		} catch (SQLException se) {
			se.printStackTrace();
		} //end try catch
		
//		PreparedService ps=new PreparedService();
//		assertTrue(ps.addStmtMember(pmVO));
		
//		fail("Not yet implemented");
	} //test

} //class
