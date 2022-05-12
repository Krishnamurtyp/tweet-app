package com.tweetapp.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tweetPostReply")
public class TweetPostReplyEntity {

	private String tweetId;
	
	private String reply;
	private String replyfrom;
	private Date created;

	private Integer like;
	
	@Id
	private String replyId;

	@Override
	public String toString() {
		return "TweetPostReplyEntity [tweetId=" + tweetId +  ", reply=" + reply
				+ ", replyfrom=" + replyfrom + ", created=" + created + ", like=" + like
				+ ", replyId=" + replyId + "]";
	}

	public TweetPostReplyEntity(String tweetId, String userName, String reply, String replyfrom, Date created,
			Date updated, Integer like, String replyId) {
		super();
		this.tweetId = tweetId;
		this.reply = reply;
		this.replyfrom = replyfrom;
		this.created = created;
		this.like = like;
		this.replyId = replyId;
	}

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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}



	public Integer getLike() {
		return like;
	}

	public void setLike(Integer like) {
		this.like = like;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public TweetPostReplyEntity() {

	}

}
