package day0325.kr.co.sist.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import day0325.kr.co.sist.vo.LobVO;

public class LobDAO {
	
	private static LobDAO lVO;

	private LobDAO() {
		
	} //LobDAO

//	singleton 생성
	public static LobDAO getInstance() {
		if( lVO==null ) {
			lVO=new LobDAO();
		} //end if
		
		return lVO;
	} //getInstance
	
	public void insertLob(LobVO lVO) throws SQLException {
		
//		1.Driver 로딩
//		2.Connection 연결
		Connection con=null;
		PreparedStatement pstmt=null;
		
		DbConnection dbConn=DbConnection.getInstance();
		
		FileInputStream fis=null;
		
		try {
			con=dbConn.getConn();
			
//			3.쿼리문을 넣어서 쿼리문 생성객체 얻기
			StringBuilder insertLob = new StringBuilder();
			insertLob
			.append("	insert	into	lob_data	")
			.append("	(num,name,email,tel,	");
			
			if( !lVO.getImg_name().isEmpty() ) {
				insertLob.append("	img,img_name,	");
			} //end if
			
			insertLob
			.append("	intro)	")
			.append("	values	")
			.append("	(seq_lob.nextval,?,?,?,	");
			
			if( !lVO.getImg_name().isEmpty() ) {
				insertLob.append("	?,?,	");
			} //end if
			insertLob.append("	?)	");
			
			System.out.println(insertLob);
			
			pstmt=con.prepareStatement( insertLob.toString() );
			
//			4.bind 변수에 값 할당
			int bindInd=0;
			
			pstmt.setString(++bindInd, lVO.getName() );
			pstmt.setString(++bindInd, lVO.getEmail() );
			pstmt.setString(++bindInd, lVO.getTel() );
			
			if( !lVO.getImg_name().isEmpty() ) {
				File file=new File( lVO.getImg_name() );
				
				if(file.exists() ) {
					fis=new FileInputStream(file);
					pstmt.setBinaryStream(++bindInd, fis, file.length() );
					pstmt.setString(++bindInd, file.getName() );
					
//					아래의 코드를 위로 요약 (사유 : try catch 한번에 처리하기 위해)
//					FileInputStream fis = null;
//					
//					try {
//						fis=new FileInputStream(file);
//					} catch (FileNotFoundException fnfe) {
//						fnfe.printStackTrace();
//					} finally {
//						if( fis != null ) {
//							try {
//								fis.close();
//							} catch (IOException ie) {
//								ie.printStackTrace();
//							} //end try catch
//						} //end if
//					} //end try catch finally
					
				} //end if
				
			} //end if
			
			pstmt.setString(++bindInd, lVO.getIntro() );
			
			System.out.println(bindInd);
			
//			5.쿼리문 수행 후 결과 얻기
			pstmt.executeUpdate();
		} catch(IOException ie) {
			ie.printStackTrace();
		} finally {
//			6.연결 끊기
			dbConn.closeDB(null, pstmt, con);
			if( fis!=null ) {
				try {
					fis.close();
				} catch (IOException ie) {
					ie.printStackTrace();
				} //end try catch
			} //end if
		} //end try catch finally
		
	} //insertLob
	
	public LobVO selectLob(int num) throws SQLException, IOException {
		LobVO lVO = null;
		
//		1.Driver 로딩
//		2.Connection 연결
		Connection con=null;
		PreparedStatement pstmt=null;
		
		ResultSet rs=null;
//		Blob
		InputStream is=null;
		FileOutputStream fos=null;
//		Clob
		BufferedReader br=null;
		
		DbConnection dbCon=DbConnection.getInstance();
		
		try {
			
			con=dbCon.getConn();
			StringBuilder selectLob=new StringBuilder();
			selectLob
			.append("	select	name,email,tel,img,img_name,intro,input_date	")
			.append("	from	lob_data")
			.append("	where	num=	")
			;
//			3.쿼리문을 넣어서 쿼리문 생성객체 얻기
			pstmt=con.prepareStatement( selectLob.toString() );
			
//			4.bind 변수에 값 할당
			pstmt.setInt(1, num);
			
//			5.쿼리문 수행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			if( rs.next() ) {
//				name,email,tel,img,img_name,intro,input_date
				lVO=new LobVO();
				
				lVO.setName(rs.getString("name"));
				lVO.setEmail(rs.getString("email"));
				lVO.setTel(rs.getString("tel"));
				lVO.setImg_name(rs.getString("img_name"));
				lVO.setInput_date(rs.getTimestamp("input_date"));
				
//				Clob
				Clob introClob=rs.getClob("intro");
				if( introClob!=null ) {
					
						br=new BufferedReader( introClob.getCharacterStream() );
						String tempIntro="";
						StringBuilder sbIntro = new StringBuilder();
						while( (tempIntro=br.readLine()) != null ) {
							sbIntro.append(tempIntro).append("\n");
						} //end while
						lVO.setIntro( sbIntro.toString() );
//						if(br!=null) { br.close(); }  //end if
				} //end if
//				System.out.println( lVO.getIntro() );
				
//				Blob
				is=rs.getBinaryStream("img");
				System.out.println(is);
				if( is!=null ) {
//					이미지 폴더 경로
					File dir=new File("c:/dev/img");
					if( !dir.exists() ) {
//						디렉토리 생성
						dir.mkdirs();
					} //end if
//					절대 경로 얻기
					File saveFile=new File( dir.getAbsolutePath()+ File.separator+ lVO.getImg_name() );
					
					fos=new FileOutputStream(saveFile);
					
					byte[] readImg=new byte[512];
					
					int dataLength=0;
					
					while( (dataLength = is.read(readImg)) != -1 ) {
//						파일 값 작성
						fos.write(readImg, 0, dataLength);
					} //end while
//					파일 값 분출
					fos.flush();
					
					System.out.println(saveFile + " / " + saveFile.exists());
				} //end if
				System.out.println(is);
				
			} //end if
			
		} finally {
//			6.연결 끊기
//			원래 introClob !=null if문 안에 while 밑에 들어갔는데 위치 옮김
			if(br!=null) {
				br.close();
			}  //end if
			
			if(is != null) {
				is.close();
			} //end if
			if(fos != null) {
				fos.close();
			} //end if
			
//			위의 먼저 닫아주고 나서 연결 끊어야 IOException 안떨어짐
			dbCon.closeDB(rs, pstmt, con);
		} //end try finally
		
		return lVO;
	} //selectLob
	
//	테스트용 main method
	public static void main(String[] args) {
//		LobVO lVO=new LobVO(0, null, null, null, "", null, null );
		
		try {
//			LobDAO.getInstance().insertLob(lVO);
//			컬럼의 값은 1부터 시작.
			LobDAO.getInstance().selectLob(3);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} //end try catch
		
//		System.out.println(lVO);
		
	} //main

} //class
