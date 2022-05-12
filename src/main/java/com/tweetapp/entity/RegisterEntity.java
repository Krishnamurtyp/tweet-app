package com.tweetapp.entity;


import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Register")


public class RegisterEntity {
	
	@Id
	private String id;
	
	@NotBlank(message="first Name must not be blank ")
	private String firstName;
	@NotBlank(message="Last Name must not be blank ")
	private String lastName;
	@NotBlank(message="Gender must not be blank ")
	private String gender;
	@NotBlank(message="UserName must not be blank ")
	private String userName;
	@NotBlank(message="DOB must not be blank ")
	private Date dob;
	@NotBlank(message="Email must not be blank ")
	private String email;
	@NotBlank(message="Password must not be blank ")
	@Size(min=5 ,max=20)
	private String password;
	public RegisterEntity(String id, String firstName, String lastName, String gender, String userName, Date dob,
			String email, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.userName = userName;
		this.dob = dob;
		this.email = email;
		this.password = password;
	}
	public String getId() {
		return id;
	}
	@Override
	public String toString() {
		return "RegisterEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", userName=" + userName + ", dob=" + dob + ", email=" + email + ", password=" + password + "]";
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	
	public RegisterEntity() {

	}
}
