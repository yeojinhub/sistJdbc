package day0325.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import day0324.kr.co.sist.pstmt.dao.PreparedDAO;
import day0324.kr.co.sist.pstmt.service.PreparedService;
import day0324.kr.co.sist.pstmt.vo.PstmtMemberVO;

class PstmtInsertTest {

	@DisplayName("PstmtMember insert 테스트")
	@Test
	void test() {
//		PreparedDAO pDAO=PreparedDAO.getInstance();
//		assertDoesNotThrow( ()-> pDAO.insertPstmtMember(pmVO) );
		
		PstmtMemberVO pmVO=new PstmtMemberVO(0,
				"이장훈", 26, "남자", "010-9999-9999", null, null);
		
		PreparedService ps=new PreparedService();
		assertTrue(ps.addPstmtMember(pmVO));
		
//		fail("Not yet implemented");
	} //test

} //class
