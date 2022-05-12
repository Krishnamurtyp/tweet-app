package com.tweetapp.request;

import javax.validation.constraints.NotBlank;

public class UserReplyTweetRequest {
	
	@NotBlank(message="tweet Id should not be blank")
	private String tweetId;
	
	@NotBlank(message="reply should not be blank")
	private String reply;
	
	@NotBlank(message="reply from should Not be Blank")
	private String replyfrom;
	
	public String getTweetId() {
		return tweetId;
	}
	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getReplyfrom() {
		return replyfrom;
	}
	public void setReplyfrom(String replyfrom) {
		this.replyfrom = replyfrom;
	}
	
	
}
