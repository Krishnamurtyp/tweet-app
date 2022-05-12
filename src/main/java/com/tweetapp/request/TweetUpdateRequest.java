package com.tweetapp.request;

import javax.validation.constraints.NotBlank;

public class TweetUpdateRequest {

	@NotBlank(message="tweet Id should not be blank")
	private String tweetId;
	@NotBlank(message="new tweet should not be blank")
	private String newTweet;

	public String getTweetId() {
		return tweetId;
	}

	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}

	public String getNewTweet() {
		return newTweet;
	}

	public void setNewTweet(String newTweet) {
		this.newTweet = newTweet;
	}

}
