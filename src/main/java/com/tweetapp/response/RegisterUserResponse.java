package com.tweetapp.response;


import java.util.Date;

import com.tweetapp.entity.RegisterEntity;





public class RegisterUserResponse {
	private String id;
	private String firstName;
	private String lastName;
	private String userName;
	private String gender;
	private Date dob;
	private String email;
	private String password;
	
	public RegisterUserResponse(RegisterEntity registerEntity) {
		this.id = registerEntity.getId();
		this.firstName = registerEntity.getFirstName();
		this.lastName = registerEntity.getLastName();
		this.userName= registerEntity.getUserName();
		this.gender = registerEntity.getGender();
		this.dob = registerEntity.getDob();
		this.email = registerEntity.getEmail();
		this.password = registerEntity.getPassword();
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RegisterUserResponse(String id, String firstName, String lastName, String userName, String gender, Date dob,
			String email, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.gender = gender;
		this.dob = dob;
		this.email = email;
		this.password = password;
	}
	

}
