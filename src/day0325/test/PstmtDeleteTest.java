package day0325.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import day0324.kr.co.sist.pstmt.dao.PreparedDAO;

class PstmtDeleteTest {

	@DisplayName("PstmtMember delete 테스트")
	@Test
	void test() {
		PreparedDAO pDAO=PreparedDAO.getInstance();
		
//		assertDoesNotThrow( ()-> pDAO.deletePstmtMember(1) );
		
		try {
			assertEquals(pDAO.deletePstmtMember(2), 1);
		} catch (SQLException se) {
			se.printStackTrace();
		} //end try catch
		
//		fail("Not yet implemented");
	} //test

} //class
