package day0402;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import day0324.kr.co.sist.dao.DbConnection;

public class UseRefCursor {

	public void selectCar(String maker) throws SQLException {
//		1. Driver 로딩
//		2. Connection 연결
		Connection con=null;
		CallableStatement cstmt=null;
//		cursor 의 제어권을 받는다.
		ResultSet rs=null;
		
		DbConnection db=DbConnection.getInstance();

		try {
			con=db.getConn();
//			3. 쿼리문 생성 객체 얻기
			cstmt=con.prepareCall(" { call select_car(?,?,?) }");
			
//			4. bind 변수 값 설정
//			in parameter
			cstmt.setString(1, maker);
//			out parameter
			cstmt.registerOutParameter(2, Types.REF_CURSOR);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			
//			5. procedure 실행
//			procedure 는 반환형 없고, out parameter 저장
//			rs=cstmt.executeQuery();
			cstmt.execute();
			
//			6. out parameter 의 저장된 값 받기
			rs=(ResultSet)cstmt.getObject(2);
			@SuppressWarnings("unused")
			String errm = cstmt.getString(3);
			
			String model="", car_year="", car_option="";
			int price=0;
			
			while( rs.next() ) {
				model=rs.getString("model");
				car_year=rs.getString("car_year");
				car_option=rs.getString("car_option");
				price=rs.getInt("price");
				
				System.out.println(model+","+car_year+","+car_option+","+price);
			} //end while
		} finally {
//			7. 연결 끊기
			db.closeDB(rs, cstmt, con);
		} //end try finally
	} //selectCar
	
	public static void main(String[] args) {
		String maker="현대";
		try {
			new UseRefCursor().selectCar(maker);
		} catch (SQLException se) {
			se.printStackTrace();
		} //end try catch
	} //main

} //class
