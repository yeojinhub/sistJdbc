package day0325.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import day0324.kr.co.sist.pstmt.dao.PreparedDAO;

class PstmtSelectTest {

	@DisplayName("PstmtMember select 테스트")
	@Test
	void test() {
		PreparedDAO pDAO=PreparedDAO.getInstance();
//		assertDoesNotThrow( ()-> pDAO.selectAllMember() );
		try {
//			총 7개의 컬럼 중 3개의 오차범위까지 허용
			assertEquals( pDAO.selectAllMember().size(), 7, 3);
		} catch (SQLException se) {
			se.printStackTrace();
		} //end try catch
		
//		fail("Not yet implemented");
	} //test

} //class
