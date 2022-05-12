package com.tweetapp.model;

import java.time.LocalDateTime;




public class CustomErrorReponse {

	

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CustomErrorReponse() {
		
	}
public CustomErrorReponse(String message) {
		this.message = message;
		this.dateTime =  LocalDateTime.now();
	}

	private LocalDateTime dateTime;
	
	private String message;
	
	
	
}
