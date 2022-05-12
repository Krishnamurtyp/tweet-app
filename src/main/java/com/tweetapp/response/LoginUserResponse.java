package com.tweetapp.response;


public class LoginUserResponse {
	
	private Boolean loginStatus;

	public Boolean getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	public LoginUserResponse(Boolean loginStatus) {
		super();
		this.loginStatus = loginStatus;
	}

}
