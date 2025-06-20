package day0327;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import day0324.kr.co.sist.dao.DbConnection;

public class ZipcodeDAO {

	private static ZipcodeDAO zDAO;
	
	private ZipcodeDAO() {
		
	} //ZipcodeDAO
	
//	Singleton 생성
	public static ZipcodeDAO getInstance() {
		if(zDAO==null) {
			zDAO=new ZipcodeDAO();
		} //end if
		return zDAO;
	} //getInstance
	
	public List<ZipcodeVO> selectZipcode(String dong) throws SQLException {
		List<ZipcodeVO> list=new ArrayList<ZipcodeVO>();
		
//		1.Driver 로딩
//		2.Connection 연결
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dbCon=DbConnection.getInstance();
		
		try {
			con=dbCon.getConn();
			
//			3.쿼리문을 넣어서 쿼리문 생성객체 얻기
			StringBuilder selectZipcode=new StringBuilder();
			selectZipcode
			.append("	select	zipcode, sido, gugun, dong,	")
			.append("	nvl(bunji,' ') bunji	")
			.append("	from	zipcode	")
			.append("	where	dong like ?||'%'	")
			;
			
			pstmt=con.prepareStatement( selectZipcode.toString() );
//			4.bind 함수 값 연결
			pstmt.setString(1, dong);
			
//			5.쿼리문 수행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			ZipcodeVO zVO=null;
			while( rs.next() ) {
				zVO=new ZipcodeVO(rs.getString("zipcode"), rs.getString("sido"),
						rs.getString("gugun"), rs.getString("dong"),
						rs.getString("bunji") );
				
				list.add(zVO);
			} //end while
			
		} finally {
//			6.연결 끊기
			dbCon.closeDB(rs, pstmt, con);
		} //end try finally
		
		return list;
	} //selectZipcode
	
	public List<ZipcodeVO> selectStmtZipcode(String dong) throws SQLException {
		List<ZipcodeVO> list=new ArrayList<ZipcodeVO>();
		
//		1.Driver 로딩
//		2.Connection 연결
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		DbConnection dbCon=DbConnection.getInstance();
		
		try {
			con=dbCon.getConn();
			
//			3.쿼리문을 넣어서 쿼리문 생성객체 얻기
			StringBuilder selectZipcode=new StringBuilder();
			selectZipcode
			.append("	select	zipcode, sido, gugun, dong,	")
			.append("	nvl(bunji,' ') bunji	")
			.append("	from	zipcode	")
			.append("	where	dong like '").append( blockInjection(dong) ).append("%'")
			;
			
//			System.out.println(selectZipcode);
			
			stmt=con.createStatement();
//			4.bind 함수 값 연결
//			pstmt.setString(1, dong);
			
//			5.쿼리문 수행 후 결과 얻기
			rs=stmt.executeQuery( selectZipcode.toString() );
			
			ZipcodeVO zVO=null;
			while( rs.next() ) {
				zVO=new ZipcodeVO(rs.getString("zipcode"), rs.getString("sido"),
						rs.getString("gugun"), rs.getString("dong"),
						rs.getString("bunji") );
				
				list.add(zVO);
			} //end while
			
		} finally {
//			6.연결 끊기
			dbCon.closeDB(rs, stmt, con);
		} //end try finally
		
		return list;
	} //selectStmtZipcode
	
	public String blockInjection(String sql) {
		String temp=sql;
		
		if( temp!=null && !temp.isEmpty() ) {
			temp=temp.replaceAll("'", "").replaceAll(" ", "").replaceAll("--", "");
		} //end if
		
		return temp;
	} //blockInjection
	
//	test 용 main method
//	public static void main(String[] args) {
//		try {
//			System.out.println(ZipcodeDAO.getInstance().selectZipcode("상도동"));
//			System.out.println(ZipcodeDAO.getInstance().selectStmtZipcode("상도동"));
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} //end try catch
//	} //main
	
} //class
