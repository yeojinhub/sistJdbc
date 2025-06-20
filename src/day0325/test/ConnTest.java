package day0325.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import day0324.kr.co.sist.dao.DbConnection;

class ConnTest {

	@DisplayName("DB연동 테스트")
	@Test
	void test() {
		DbConnection dbCon = DbConnection.getInstance();
		
		assertDoesNotThrow( ()-> dbCon.getConn() );
		
//		fail("Not yet implemented");
	} //test

} //class
