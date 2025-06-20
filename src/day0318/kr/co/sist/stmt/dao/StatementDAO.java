package day0318.kr.co.sist.stmt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import day0318.kr.co.sist.stmt.vo.StatementMemberVO;

/**
 * CRUD (Create/Read/Update/Delete)
 * C : create, insert
 * R : select
 * U : update, alter
 * D : delete, drop, truncate
 */
public class StatementDAO {
	
	/**
	 * STATEMENT_MEMBER 테이블에 레코드를 추가하는 method
	 * @param smVO 추가할 값
	 * @throws SQLException 예외
	 */
	public void insertStmtMember(StatementMemberVO smVO) throws SQLException{
//		1. Driver 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} //end try catch
		 
//		2. 로딩된 Driver 를 사용하여 Connection 얻기
		Connection conn=null;
		Statement stmt=null;
		
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		try {
			conn=DriverManager.getConnection(url,id,pass);
//		3. Connection 에서 쿼리문 생성객체 얻기
			stmt=conn.createStatement();
//		4. 쿼리문 실행 후 결과 얻기
			StringBuilder insertStmtMember=new StringBuilder();
			insertStmtMember
			.append("insert into statement_member(num,name,age,gender,tel)")
			.append("values(seq_stmt.nextval, '").append(smVO.getName())
			.append("',").append(smVO.getAge())
			.append(",'").append(smVO.getGender())
			.append("','").append(smVO.getTel()).append("')");
			
			stmt.execute(insertStmtMember.toString());
			
		} finally {
			if(stmt!=null) {
				stmt.close();
			} //end if
			if(conn!=null) {
				conn.close();
			} //end if
		} //end try finally
		
//		5. 연결 끊기
	} //insertStmtMember
	
	/**
	 * STATEMENT_MEMBER 테이블에 레코드를 변경하는 method
	 * 번호를 사용하여 나이와 전화번호를 변경하는 일
	 * 0건 : 조건에 사용되는 값이 잘못되어있을 때
	 * n건 : 조건에 사용되는 값에 해당하는 레코드가 여러행일 때
	 * 예외 : 쿼리문이 잘못된 경우, DB연동에 문제가 발생한 경우
	 * @param smvo 변경할 값을 가진 객체
	 * @return 변경된 행의 수
	 * @throws SQLException 예외
	 */
	public int updateStmtMember(StatementMemberVO smVO) throws SQLException{
//		변경된 행의 수를 저장할 method
		int rowCnt=0;
		
//		1.Driver 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} //end try catch
		
//		2.Connection 얻기
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		Connection conn=null;
		Statement stmt=null;
		
		try {
			conn=DriverManager.getConnection(url,id,pass);
//		3.쿼리문 생성객체 얻기
			stmt=conn.createStatement();
//		4.쿼리문 수행 후 결과 얻기
			StringBuilder updateStmtMember=new StringBuilder();
			updateStmtMember
			.append("	update	statement_member	")
			.append("	set	age=").append(smVO.getAge()).append(", tel='")
			.append(smVO.getTel()).append("'")
			.append("	where	num=").append(smVO.getNum());
			
			rowCnt=stmt.executeUpdate(updateStmtMember.toString());
		} finally {
//		5.연결 끊기
			if(stmt!=null) {
				stmt.close();
			} //end if
			if(conn!=null) {
				conn.close();
			} //end if
		} //end try finally
		
		return rowCnt;
	} //updateStmtMember
	
	/**
	 * STATEMENT_MEMBER 테이블의 레코드를 삭제하는 method
	 * 번호를 사용하여 나이와 전화번호를 삭제하는 일
	 * 0건 : 조건에 사용되는 값이 잘못되어있을 때
	 * n건 : 조건에 사용되는 값에 해당하는 레코드가 여러행일 때
	 * 예외 : 쿼리문이 잘못된 경우, DB연동에 문제가 발생한 경우
	 * @param smvo 변경할 값을 가진 객체
	 * @return 변경된 행의 수
	 * @throws SQLException 예외
	 */
	public int deleteStmtMember(int num) throws SQLException{
//		삭제된 행의 수를 저장할 method
		int rowCnt=0;
		
//		1.Driver 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} //end try catch
		
//		2.Connection 얻기
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		Connection conn=null;
		Statement stmt=null;
		
		try {
			conn=DriverManager.getConnection(url,id,pass);
//		3.쿼리문 생성객체 얻기
			stmt=conn.createStatement();
//		4.쿼리문 수행 후 결과 얻기
			StringBuilder deleteStmtMember=new StringBuilder();
			deleteStmtMember
			.append("	delete	from	statement_member	")
			.append("	where	num=").append(num);
			
			rowCnt=stmt.executeUpdate(deleteStmtMember.toString());
		} finally {
//		5.연결 끊기
			if(stmt!=null) {
				stmt.close();
			} //end if
			if(conn!=null) {
				conn.close();
			} //end if
		} //end try finally
		
		return rowCnt;
	} //deleteStmtMember
	
	/**
	 * STATEMENT_MEMBER 테이블에 레코드를 검색하는 method
	 * 검색된 레코드 하나를 VO에 저장, 모든 VO객체를 List 에 저장하여 반환
	 * @return 모든 레코드를 가진 리스트
	 * @throws SQLException 예외
	 */
	public List<StatementMemberVO> selectAllStmtMember()  throws SQLException{
		List<StatementMemberVO> list=new ArrayList<StatementMemberVO>();
		
//		1.Driver 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} //end try catch
		
		Connection conn=null;	//DBMS와 연결 유지, auto commit 설정, 쿼리문 생성객체 얻기.
		Statement stmt=null;	//쿼리문을 실행하는
		ResultSet rs=null;		//cursor의 제어권을 받기, DBMS 데이터 형을 Java의 데이터형으로 받기.
		
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		try {
//		2.Connection 얻기
			conn=DriverManager.getConnection(url,id,pass);
//		3.쿼리문 생성객체 얻기
			stmt=conn.createStatement();
//		4.쿼리문 수행 후 결과 얻기
			StringBuilder selectAllMember=new StringBuilder();
			selectAllMember.append("	select	num, name, age, gender, tel, input_date	")
			.append("	from	statement_member	");
			
			rs=stmt.executeQuery(selectAllMember.toString());
//			쿼리를 수행한 후 생성된 inline view 앞에 생성된 cursor 의 제어권을 얻기.
			
//			몇 줄의 레코드가 있는지 알 수 없음.
			StatementMemberVO smVO=null;
			while(rs.next()) {	//레코드가 존재하면
//				System.out.println(rs.getString("name"));
				
//				컬럼의 값을 가져와서 VO에 설정한다.
				smVO=new StatementMemberVO();
				smVO.setNum(rs.getInt("num"));					//정수
				smVO.setName(rs.getString("name"));				//문자열
				smVO.setAge(rs.getInt("age"));					//정수
				smVO.setGender(rs.getString("gender"));			//문자열
				smVO.setTel(rs.getString("tel"));				//문자열
				smVO.setInputDate(rs.getDate("input_date"));	//날짜
				
//				생성된 레코드를 리스트에 추가
				list.add(smVO);
			} //end while
			
//			System.out.println(list);
			
		} finally {
//		5.연결 끊기
			if(rs!=null) {
				rs.close();
			} //end if
			if(stmt!=null) {
				stmt.close();
			} //end if
			if(conn!=null) {
				conn.close();
			} //end if
		} //end try finally
		
		return list;
	} //selectAllMember
	
	/**
	 * STATEMENT_MEMBER 테이블에서 번호에 해당하는 레코드를 하나를 검색하는 일
	 * @param num 번호
	 * @return 레코드 하나를 가진 VO객체
	 * @throws SQLException 예외
	 */
	public StatementMemberVO selectOneStmtMember(int num) throws SQLException{
		StatementMemberVO smVO=null;
		
		return smVO;
	} //selectOneStmtMember
	
	/**
	 * STATEMENT_MEMBER	테이블의 모든 레코드 수를 얻기
	 * @return 레코드 수
	 * @throws SQLException 예외처리
	 */
	public int selectCntStmtMember() {
		int cnt=0;
//		1.Driver 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} //end try catch
		
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
//		2.Connection 얻기
//		3.쿼리문 생성객체 얻기
//		5.자동으로 연결 끊기 (try~with~resources)
		String selectAllCnt="select count(num) cnt from statement_member";
		try(Connection con = DriverManager.getConnection(url,id,pass);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(selectAllCnt)) {
//		4.쿼리문 실행 후 결과 얻기
			if(rs.next()) {
				cnt=rs.getInt("cnt");
			} //end if
		} catch(SQLException se) {
			se.printStackTrace();
		} //end try catch
		
		return cnt;
	} // selectCntStmtMember
	
} //class
