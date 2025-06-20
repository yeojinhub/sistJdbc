package day0325.kr.co.sist.service;

import java.io.IOException;
import java.sql.SQLException;

import day0325.kr.co.sist.dao.LobDAO;
import day0325.kr.co.sist.vo.LobVO;

public class LobService {

	public boolean addMember(LobVO lVO) {
		boolean flag=false;
		LobDAO lDAO=LobDAO.getInstance();
		
		try {
			lDAO.insertLob(lVO);
			flag=true;
		} catch (SQLException se) {
			se.printStackTrace();
		} //end try catch
		
		return flag;
	} //addMember
	
	public LobVO searchMember(int num) {
		LobVO lVO=null;
		
		try {
			lVO= LobDAO.getInstance().selectLob(num);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} //end try catch
		
		System.out.println("searchMember");
		return lVO;
	}// searchMember
	
} //class
