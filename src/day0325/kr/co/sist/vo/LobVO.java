package day0325.kr.co.sist.vo;

import java.sql.Timestamp;

public class LobVO {
	
	private int num;
	private String name;
	private String email;
	private String tel;
	private String img_name;
	private String intro;
	private Timestamp input_date;
	
	public LobVO() {
	}

	public LobVO(int num, String name, String email, String tel, String img_name, String intro,
			Timestamp input_date) {
		this.num = num;
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.img_name = img_name;
		this.intro = intro;
		this.input_date = input_date;
	}
	
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getImg_name() {
		return img_name;
	}
	
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	
	public String getIntro() {
		return intro;
	}
	
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	public Timestamp getInput_date() {
		return input_date;
	}
	
	public void setInput_date(Timestamp input_date) {
		this.input_date = input_date;
	}

	@Override
	public String toString() {
		return "LobVO [num=" + num + ", name=" + name + ", email=" + email + ", tel=" + tel + ", img_name=" + img_name
				+ ", intro=" + intro + ", input_date=" + input_date + "]";
	}
	
} //class
