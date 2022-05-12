package com.tweetapp.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginUserRequest {
	
	@NotBlank(message="username should not be blank")
	private String userName;
	
	@NotBlank(message="password should not be blank")
	@Size(min=5,max=20,message="password length should be min 5")
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
