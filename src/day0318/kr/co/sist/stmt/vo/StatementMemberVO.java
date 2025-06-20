package day0318.kr.co.sist.stmt.vo;

import java.sql.Date;

public class StatementMemberVO {	

	private int num;
	private String name;
	private int age;
	private String gender;
	private String tel;
	private Date inputDate;
	
	public StatementMemberVO() {
	} //StatementMemberVO

	public StatementMemberVO(int num, String name, int age, String gender, String tel, Date inputDate) {
		this.num = num;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.tel = tel;
		this.inputDate = inputDate;
	} //StatementMemberVO

	public int getNum() {
		return num;
	} //getNum

	public void setNum(int num) {
		this.num = num;
	} //setNum

	public String getName() {
		return name;
	} //getName

	public void setName(String name) {
		this.name = name;
	} //setName

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


	public Date getInputDate() {
		return inputDate;
	}


	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	
	@Override
	public String toString() {
		return "StatementMemberVO [num=" + num + ", name=" + name + ", age=" + age + ", gender=" + gender + ", tel="
				+ tel + ", inputDate=" + inputDate + "]";
	}

} //class
