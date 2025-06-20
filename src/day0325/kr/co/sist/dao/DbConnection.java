package day0325.kr.co.sist.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbConnection {
	private static DbConnection dbCon;
	
	private DbConnection() {
		
	} //DbConnection
	
	public static DbConnection getInstance() {
		if(dbCon == null) {
			dbCon=new DbConnection();
		} //end if
		
		return dbCon;
	} //DbConnection getInstatnce
	
	public Connection getConn() throws SQLException {
//		properties 파일 사용(소스코드에 계정정보를 하드코딩하지 않는다.)
		String currentDir=System.getProperty("user.dir");
		File file=new File(currentDir+"/src/day0325/properties/database.properties");
		if( !file.exists() ) {
			throw new SQLException("database.properties가 지정된 경로에 존재하지 않습니다.");
		} //end if
		
//		properties 생성
		Properties prop=new Properties();
//		properties File 로딩
		@SuppressWarnings("unused")
		String driver="";
		String url="";
		String id="";
		String pass="";
		
		try {
			prop.load(new FileInputStream(file));
			driver=prop.getProperty("driverClass");
			url=prop.getProperty("url");
			id=prop.getProperty("id");
			pass=prop.getProperty("pass");
		} catch (IOException ie) {
			ie.printStackTrace();
		} //end try catch
		
		Connection con=null;
//		1. Driver 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} //end try catch
		
//		2. Connection 얻기
//		String url="";
//		String id="";
//		String pass="";
		
		con=DriverManager.getConnection(url, id, pass);
		
		return con;
	} //getConn
	
//	public static void main(String[] args) {
//		try {
//			System.out.println(new DbConnection().getConn());
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} //end try catch
//	} //main
	
	public void closeDB(ResultSet rs, Statement stmt, Connection con) throws SQLException {
		try {
			if(rs != null) {
				rs.close();
			} //end if
			if(stmt != null) {
				stmt.close();
			} //end if
		} finally {
			if(con != null) {
				con.close();
			} //end if
		} //end try finally
	} //closeDB
	
} //class
