package com.tweetapp.response;

import java.util.Date;

import com.tweetapp.entity.TweetPostReplyEntity;

public class UserReplyTweetResponse {
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}



	public String getReply() {
		return reply;
	}


	public void setReply(String reply) {
		this.reply = reply;
	}


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public String getReplyfrom() {
		return replyfrom;
	}


	public void setReplyfrom(String replyfrom) {
		this.replyfrom = replyfrom;
	}


	private String id;
	public UserReplyTweetResponse(String id, String userName, String reply, Date created, String replyfrom) {
		super();
		this.id = id;
		this.reply = reply;
		this.created = created;
		this.replyfrom = replyfrom;
	}


	private String reply;
	private Date created;
	private String replyfrom;
	
	
	public UserReplyTweetResponse(TweetPostReplyEntity tweetPostReplyEntity) {

		this.reply=tweetPostReplyEntity.getReply();
		this.created=tweetPostReplyEntity.getCreated();
		this.replyfrom=tweetPostReplyEntity.getReplyfrom();
		}

}
