package com.tweetapp.request;

//import javax.persistence.Id;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Pattern;






public class UserCredentials {

		
	    public UserCredentials(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

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

		//@NotEmpty(message="UserName cannot be Empty")
		private String userName;
		
	    //@NotEmpty(message="Password cannot be Empty")
		private String password;
	    
}
