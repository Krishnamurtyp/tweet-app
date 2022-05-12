package com.tweetapp.response;

import java.util.Date;

import com.tweetapp.entity.TweetPostEntity;



public class UserNewTweetResponse {
	
	public UserNewTweetResponse(String id, String userName, String tweet, Integer like, String reply, Date created,
			Date updated) {
		super();
		this.id = id;
		this.userName = userName;
		this.tweet = tweet;
		this.like = like;
		this.reply = reply;
		this.created = created;
		this.updated = updated;
	}

	private String id;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	private String userName;     
	private String tweet;
	private Integer like;
	private String reply;
	private Date created;
	private Date updated;
	
	public UserNewTweetResponse(TweetPostEntity tweetPostEntity) {
		this.id = tweetPostEntity.getId();
		this.userName = tweetPostEntity.getUserName();
		this.tweet = tweetPostEntity.getTweet();
		this.like = tweetPostEntity.getLike();
		//this.reply = tweetPostEntity.getReply();
		this.created = tweetPostEntity.getCreated();
		this.updated = tweetPostEntity.getUpdated();
		
		}
	
	
}
