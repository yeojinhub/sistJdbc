package day0320.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.SQLException;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import day0318.kr.co.sist.stmt.dao.StatementDAO;

class StmtSelectTest {

	@DisplayName("select test")
	@Test
	void test() {
		StatementDAO sDAO=new StatementDAO();
//		assertNotEquals(sDAO.selectCntStmtMember(), 0);
		
		try {
//			assertEquals(sDAO.selectAllStmtMember(), 4);
//			오차범위 : 조회된레코드 수, 예상 조회 레코드 수, 오차 범위
			assertEquals(sDAO.selectAllStmtMember().size(), 4,4); //데이터 값이 0~8개 라면 성공, 그 이상은 실패
			
		} catch (SQLException se) {
			se.printStackTrace();
		} //end try catch
		
//		fail("Not yet implemented");
	} //test

} //class
