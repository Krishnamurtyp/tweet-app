package com.tweetapp.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "tweetPost")
public class TweetPostEntity {
	
	@Override
	public String toString() {
		return "TweetPostEntity [id=" + id + ", userName=" + userName + ", tweet=" + tweet + ", like=" + like
				+ ", reply=" + reply + ", created=" + created + ", updated=" + updated + "]";
	}
	@Id
	private String id;
	public TweetPostEntity(String id, String userName, String tweet, Integer like, String reply, Date created,
			Date updated) {
		super();
		this.id = id;
		this.userName = userName;
		this.tweet = tweet;
		this.like = like;
		this.reply.add(reply);
		this.created = created;
		this.updated = updated;
	}
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
	private List<String> reply;
	
	
	public List<String> getReply() {
		return reply;
	}
	public void setReply(List<String> reply) {
		this.reply = reply;
	}
	private Date created;
	private Date updated;
	public TweetPostEntity() {
		
	}
}
