package day0401;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

import day0324.kr.co.sist.dao.DbConnection;

public class UseInsertProc {

	public void insertCpEmp(CpEmpVO ceVO) throws SQLException {
//		1. Driver 로딩
//		2. Connection 연결
		Connection con=null;
		CallableStatement cstmt=null;
		
		DbConnection db=DbConnection.getInstance();
		try {
			con=db.getConn();
			
//			3. 쿼리문을 넣어서 쿼리문 생성객체 얻기
			cstmt=con.prepareCall( "{ call insert_cp_emp(?,?,?,?, ?,?) }" );
			
//			4. bind 변수에 값 할당
//			in parameter
			cstmt.setInt(1, ceVO.getEmpno());
			cstmt.setString(2, ceVO.getEname());
			cstmt.setString(3, ceVO.getJob());
			cstmt.setInt(4, ceVO.getSal());
			
//			out parameter
			cstmt.registerOutParameter(5, Types.NUMERIC );
			cstmt.registerOutParameter(6, Types.VARCHAR );
			
//			5. 쿼리문 수행 후 결과 얻기
			cstmt.execute();
			
//			6. 쿼리문 수행 후 out parameter 에 존재하는 값 얻기
			int cnt=cstmt.getInt(5);
//			sql % rowcount
			String msg=cstmt.getString(6);
			
			String outMsg="추가실패";
			if(cnt ==1 ) {
				outMsg="추가성공!";
			} //end if
			
			JOptionPane.showMessageDialog(null, outMsg);
			System.out.println("PL/SQL에서 생성한 메세지 : "+msg);
		} finally {
//			7. 연결 끊기
			db.closeDB(null, cstmt, con);
		} //end try finally
	} //insertCpEmp
	
	public static void main(String[] args) {
		
		
		CpEmpVO ceVO=new CpEmpVO();
		ceVO.setEmpno(2);
		ceVO.setEname("이장훈");
		ceVO.setJob("개발자");
		ceVO.setSal(3600);
		
		try {
			new UseInsertProc().insertCpEmp(ceVO);
		} catch (SQLException se) {
			se.printStackTrace();
		} //end try catch
		
	} //main

} //class
