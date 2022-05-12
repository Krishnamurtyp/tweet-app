package com.tweetapp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tweetapp.entity.RegisterEntity;
import com.tweetapp.entity.TweetPostEntity;
import com.tweetapp.entity.TweetPostReplyEntity;
import com.tweetapp.repository.TweetAppRepository;
import com.tweetapp.repository.TweetPostReplyRepository;
import com.tweetapp.repository.TweetPostRepository;
import com.tweetapp.request.LoginUserRequest;
import com.tweetapp.request.RegisterUserRequest;
import com.tweetapp.request.TweetUpdateRequest;
import com.tweetapp.request.UserNewTweetRequest;
import com.tweetapp.request.UserReplyTweetRequest;
import com.tweetapp.response.RegisterUserResponse;
import com.tweetapp.response.UserNewTweetResponse;
import com.tweetapp.response.UserReplyTweetResponse;

@Service
public class TweetappServicesImpl {
	
	@Autowired
	TweetAppRepository tweetAppRepository;
	
	@Autowired
	TweetPostRepository tweetPostRepository;
	
	@Autowired
	TweetPostReplyRepository tweetPostReplyRepository;
	
	
	@Transactional
	public RegisterUserResponse createUser(RegisterUserRequest registerUserRequest) {
		RegisterEntity registerEntity=new RegisterEntity();
		registerEntity.setFirstName(registerUserRequest.getFirstName());
		registerEntity.setLastName(registerUserRequest.getLastName());
		registerEntity.setUserName(registerUserRequest.getUserName());
		registerEntity.setGender(registerUserRequest.getGender());
		registerEntity.setDob(registerUserRequest.getDob());
		registerEntity.setEmail(registerUserRequest.getEmail());
		registerEntity.setPassword(registerUserRequest.getPassword());
		registerEntity = tweetAppRepository.save(registerEntity);
		
		return new RegisterUserResponse(registerEntity);
	}
	
	public UserNewTweetResponse createUserTweet(UserNewTweetRequest userNewTweetRequest) {
		
		TweetPostEntity tweetPostEntity = new TweetPostEntity();
		tweetPostEntity.setUserName(userNewTweetRequest.getUserName());
		tweetPostEntity.setTweet(userNewTweetRequest.getTweet());
		tweetPostEntity.setReply(new ArrayList<>());
		tweetPostEntity.setLike(0);
		tweetPostEntity.setCreated(new Date());
		tweetPostEntity.setUpdated(null);
		
		tweetPostEntity = tweetPostRepository.save(tweetPostEntity);
		return new UserNewTweetResponse(tweetPostEntity);
	}
	
	public UserReplyTweetResponse createUserReplyTweet(UserReplyTweetRequest userReplyTweetRequest) {
		
		TweetPostReplyEntity tweetPostReplyEntity = new TweetPostReplyEntity();
		tweetPostReplyEntity.setTweetId(userReplyTweetRequest.getTweetId());
		tweetPostReplyEntity.setReply(userReplyTweetRequest.getReply());
		tweetPostReplyEntity.setReplyfrom(userReplyTweetRequest.getReplyfrom());
		tweetPostReplyEntity.setLike(0);
		tweetPostReplyEntity.setCreated(new Date());
		Optional<TweetPostEntity> tweetPostEntity = Optional.ofNullable(new TweetPostEntity());
		tweetPostEntity = tweetPostRepository.findById(userReplyTweetRequest.getTweetId());
		tweetPostReplyEntity = tweetPostReplyRepository.save(tweetPostReplyEntity);
		if(!tweetPostEntity.isEmpty()) {
			List<String> replies = tweetPostEntity.get().getReply();
			
			replies.add(tweetPostReplyEntity.getReplyId());
			tweetPostEntity.get().setReply(replies);
			TweetPostEntity tweet = tweetPostEntity.get();
			tweet = tweetPostRepository.save(tweet);
		}
		return new UserReplyTweetResponse(tweetPostReplyEntity);
	}
	
	public boolean isExists(String id) {
		  return tweetPostRepository.existsById(id);
	}
	
	public List<TweetPostEntity> getAllTweet(){
		return tweetPostRepository.findAll();
	}
	
	public Optional<TweetPostEntity> getUserTweet(String id){
		return tweetPostRepository.findById(id);
	}
	
	public void deleteUserTweet(String id){
		 tweetPostRepository.deleteById(id);   
	}
	
	public UserNewTweetResponse updateUserTweet(TweetUpdateRequest tweetUpdateRequest) {
		
		Optional<TweetPostEntity> tweetPostEntity = Optional.ofNullable(new TweetPostEntity());
		tweetPostEntity = tweetPostRepository.findById(tweetUpdateRequest.getTweetId());
		tweetPostEntity.get().setTweet(tweetUpdateRequest.getNewTweet());
		TweetPostEntity tweet = tweetPostEntity.get();
		tweet = tweetPostRepository.save(tweet);
		return new UserNewTweetResponse(tweet);
	}
	
      public RegisterUserResponse resetUserPassword(LoginUserRequest loginUserRequest) {
		
		RegisterEntity user = new RegisterEntity();
		user = tweetAppRepository.findByUserName(loginUserRequest.getUserName());
		user.setPassword(loginUserRequest.getPassword());
		user = tweetAppRepository.save(user);
		return new RegisterUserResponse(user);
	}

	
	public UserNewTweetResponse likeaTweet(TweetPostEntity tweetPost) {
		Optional<TweetPostEntity> tweetPostEntity = Optional.ofNullable(new TweetPostEntity());
		tweetPostEntity = tweetPostRepository.findById(tweetPost.getId());
		tweetPostEntity.get().setLike(tweetPostEntity.get().getLike()+1);
		TweetPostEntity tweet = tweetPostEntity.get();
		tweet = tweetPostRepository.save(tweet);
		return new UserNewTweetResponse(tweet);
	}
	
	
	public List<TweetPostEntity> viewAllMyTweets(String username) {
		Optional<List<TweetPostEntity>> tweetPostEntity = Optional.ofNullable(new ArrayList<>());
		tweetPostEntity= Optional.of(tweetPostRepository.findByUserName(username));
		List<TweetPostEntity> tweets = tweetPostEntity.get();
		return tweets;
	}
	
	
	public boolean forgotPassword(LoginUserRequest loginUserRequest,String email) {
		RegisterEntity user = new RegisterEntity();
		user = tweetAppRepository.findByUserName(loginUserRequest.getUserName());
	    if(user.getUserName().equals(loginUserRequest.getUserName()) && user.getEmail().equals(email)) {
	    	user.setPassword(loginUserRequest.getPassword());
	    	tweetAppRepository.save(user);
	    	return true;
	    }else {
	    	return false;
	    }
		
		
	}
	
	
	public List<TweetPostReplyEntity> getAllReplyTweets(String tweetId){
		Optional<TweetPostEntity> tweetPostEntity = Optional.ofNullable(new TweetPostEntity());
		tweetPostEntity = tweetPostRepository.findById(tweetId);
		List<TweetPostReplyEntity> replies = new ArrayList<>();
		List<String> replyList = tweetPostEntity.get().getReply();
		for(String s:replyList) {
		 Optional<TweetPostReplyEntity> tweetPostReply =tweetPostReplyRepository.findById(s);
		 replies.add(tweetPostReply.get());
		}
		return replies;
	}
    

}
