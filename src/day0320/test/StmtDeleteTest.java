package day0320.test;

import static org.junit.jupiter.api.Assertions.*;

//import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import day0318.kr.co.sist.stmt.dao.StatementDAO;
import day0318.kr.co.sist.stmt.service.StatementService;

class StmtDeleteTest {

	@DisplayName("delete 테스트")
	@Test
	void test() {
//		StatementDAO sd=new StatementDAO();
//		
//		try {
//			assertEquals(sd.deleteStmtMember(1), 1);		//반환된 값이 해당 값과 같다면
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} //end try catch
		
		int num=2;
		StatementService ss=new StatementService(null);
		assertTrue(ss.removeStmtMember(num));		//true 면 성공, false 면 실패
		
		fail("Not yet implemented");
	} //test

} //class
