package com.tweetapp.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterUserRequest {
	@NotBlank(message = "first Name must not be blank ")
	private String firstName;
	
	private String lastName;
	@NotBlank(message = "Gender must not be blank ")
	private String gender;
	@NotBlank(message = "UserName must not be blank ")
	private String userName;
	
	private Date dob;
	@NotBlank(message = "Email must not be blank ")
	private String email;
	@NotBlank(message = "Password must not be blank ")
	@Size(min = 5, max = 20)
	private String password;

	public RegisterUserRequest(String firstName, String lastName, String userName, String gender, Date dob,
			String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.gender = gender;
		this.dob = dob;
		this.email = email;
		this.password = password;
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

	public RegisterUserRequest() {
	}

}
