package day0320.test;

import static org.junit.jupiter.api.Assertions.*;

//import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import day0318.kr.co.sist.stmt.dao.StatementDAO;
import day0318.kr.co.sist.stmt.service.StatementService;
import day0318.kr.co.sist.stmt.vo.StatementMemberVO;

class StmtTest {

	@DisplayName("insert 테스트")
	@Test
	void test() {
//		StatementMemberVO smVO=
//				new StatementMemberVO(10,"테스트",20,"남자3333","010-1111-1111", null);
		
//		StatementService ss=new StatementService(null);
//		ss.addStmtMember(smVO);
		
//		StatementDAO sd=new StatementDAO();
//		assertDoesNotThrow( () -> sd.insertStmtMember(smVO) );
		
		StatementMemberVO smVO=
				new StatementMemberVO(1,null,27,null,"010-9999-9999", null);
//		StatementDAO sd=new StatementDAO();
		
//		try {
//			assertEquals(sd.updateStmtMember(smVO), 1);		//반환된 값이 해당 값과 같다면
//			assertNotEquals(sd.updateStmtMember(smVO), 0);	//반환된 값이 해당값과 같지 않다면
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} //end try catch
		
//		assertDoesNotThrow( () -> sd.updateStmtMember(smVO) );	//예외가 발생하지 않으면 성공
//		assertThrows( SQLException.class ,() -> sd.updateStmtMember(smVO) );	//예외 발생시 성공
		
		
		StatementService ss=new StatementService(null);
		assertTrue(ss.modifyStmtMember(smVO));		//true 면 성공, false 면 실패
		
//		fail("Not yet implemented");
	} //test

} //class
