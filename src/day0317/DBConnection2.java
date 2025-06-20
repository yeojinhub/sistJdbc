package day0317;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DBMS와 Connection 얻기.
 */
public class DBConnection2 {

	public DBConnection2() throws SQLException{
		super();
		
//		1. Driver 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver 로딩 성공");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} //end try catch
		
//		2. Connection 얻기
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		Connection con=null;
		Statement stmt=null;
		
		try {
//			2. Connection 얻기
			con = DriverManager.getConnection(url,id,pass);
			System.out.println("DB연동 객체 : "+con);
//			3. 쿼리문 생성 객체 얻기
			stmt=con.createStatement();	//실행할 쿼리문을 모른다.
//			4. 쿼리문 수행 후 결과 얻기
			int deptno=99;
			String dname="SM";
			String loc="대구";
			
//			String insertDept2="insert into dept2(deptno, dname, loc) values("+deptno
//					+",'"+dname+"', '"+loc+"')";
			StringBuilder insertDept2=new StringBuilder();
			insertDept2.append("insert into dept2(deptno, dname,loc) values(")
			.append(deptno).append(",'").append(dname).append("','")
			.append(loc).append("')");
			
			System.out.println(insertDept2);
			
//			int cnt=stmt.executeUpdate(insertDept2.toString());
//			System.out.println(cnt+"건 추가되었습니다.");
		
		} finally{
//			5. 연결 끊기
			if(stmt!=null) {
				stmt.close();
			} //end if
			if(con!=null) {
				con.close();
			} //end if
		} //end try finally
		
	} //DBConnection
	
	public static void main(String[] args) {
		try { 
			new DBConnection2();
		} catch (SQLException se) {
			se.printStackTrace();
		} //end try catch
		
	} //main

} //class
