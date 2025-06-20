package day0326.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import day0325.kr.co.sist.dao.DbConnection;

class ConnectionTest {

	@DisplayName("LobDAO db연결 테스트")
	@Test
	void test() {
		DbConnection dbcon=DbConnection.getInstance();
		assertDoesNotThrow( ()-> dbcon.getConn() );
		
//		fail("Not yet implemented");
	} //test

} //class
