package com.tweetapp.response;



public class ForgetPasswordResponse {
	
	private String statusCode;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public ForgetPasswordResponse(String statusCode) {
		super();
		this.statusCode = statusCode;
	}	

}
