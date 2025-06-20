package day0325.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import day0324.kr.co.sist.pstmt.dao.PreparedDAO;

class PstmtSelectOneTest {

	@DisplayName("PstmtMember selectOne 한 행 조회 테스트")
	@Test
	void test() {
		int num=1;
		
		PreparedDAO pDAO=PreparedDAO.getInstance();
//		assertDoesNotThrow( ()-> pDAO.selectOneMember(num) );
		
		num=0;
		try {
			assertNotNull(pDAO.selectOneMember(num));
		} catch (SQLException se) {
			se.printStackTrace();
		} //end try catch
		
//		fail("Not yet implemented");
	} //test

} //class
