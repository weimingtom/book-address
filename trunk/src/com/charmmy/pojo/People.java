package com.charmmy.pojo;

public class People {

	public People(){}
	
	public People(int id, String phone) {
		this.id = id;
		this.phone = phone;
	}
	
	public People(String name, String phone, String tel, String email,
			String address, String backContent) {
		super();
		this.name = name;
		this.phone = phone;
		this.tel = tel;
		this.email = email;
		this.address = address;
		this.backContent = backContent;
	}

	public People(int id, String name, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
	}



	private int id;
	
	//姓名
	private String name;  
	
	//手机号码	
	private String phone;   
	
	//电话号码
	private String tel; 
	
	//电子邮件
	private String email; 
	
	//地址
	private String address;
	
	//备注
	private String backContent;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBackContent() {
		return backContent;
	}

	public void setBackContent(String backContent) {
		this.backContent = backContent;
	}
	
}
