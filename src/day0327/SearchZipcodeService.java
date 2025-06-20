package day0327;

import java.sql.SQLException;
import java.util.List;

public class SearchZipcodeService {
	
	public List<ZipcodeVO> searchzipZipcode(String dong){
		List<ZipcodeVO> list = null;
		
		try {
//			list = ZipcodeDAO.getInstance().selectZipcode(dong);
			list = ZipcodeDAO.getInstance().selectStmtZipcode(dong);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		
		return list;
	}//searchzipZipcode
	
}//class 
