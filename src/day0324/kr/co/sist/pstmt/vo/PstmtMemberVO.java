package day0324.kr.co.sist.pstmt.vo;

import java.util.Date;

public class PstmtMemberVO {
	private int num;
	private String name;
	private int age;
	private String gender;
	private String tel;
	private Date inputdate;
	private String strInputDate;
	
	
	public PstmtMemberVO() {
	}

	public PstmtMemberVO(int num, String name, int age, String gender, String tel, Date inputdate,
			String strInputDate) {
		this.num = num;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.tel = tel;
		this.inputdate = inputdate;
		this.strInputDate = strInputDate;
	} //PstmtMemberVO

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getInputdate() {
		return inputdate;
	}

	public void setInputdate(Date inputdate) {
		this.inputdate = inputdate;
	}

	public String getStrInputDate() {
		return strInputDate;
	}

	public void setStrInputDate(String strInputDate) {
		this.strInputDate = strInputDate;
	}

	@Override
	public String toString() {
		return "PstmtMemberVO [num=" + num + ", name=" + name + ", age=" + age + ", gender=" + gender + ", tel=" + tel
				+ ", inputdate=" + inputdate + ", strInputDate=" + strInputDate + "]";
	} //toString
	
	
} //class
