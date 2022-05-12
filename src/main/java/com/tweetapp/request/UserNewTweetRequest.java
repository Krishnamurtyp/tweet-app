package com.tweetapp.request;

import javax.validation.constraints.NotBlank;

public class UserNewTweetRequest {
	
	private String id;
	@NotBlank(message="userName should not blank")
	private String userName;
	
	@NotBlank(message="tweet should not be blank")
	private String tweet;
	
	private Integer like;
	private String reply;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	public Integer getLike() {
		return like;
	}
	public void setLike(Integer like) {
		this.like = like;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	
	
}
