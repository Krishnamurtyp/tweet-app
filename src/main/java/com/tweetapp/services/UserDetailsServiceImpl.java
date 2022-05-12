package com.tweetapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.tweetapp.entity.RegisterEntity;
import com.tweetapp.repository.TweetAppRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private TweetAppRepository tweetAppRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<RegisterEntity> user = Optional.ofNullable(tweetAppRepository.findByUserName(username));
		RegisterEntity user1 = user.get();
		return new User(user1.getUserName(), user1.getPassword(), new ArrayList<>());
	}

	public List<RegisterEntity> getUserList() {
		return tweetAppRepository.findAll();
	}
	
	public RegisterEntity getUserDetails(String username) {
		Optional<RegisterEntity> user = Optional.ofNullable(tweetAppRepository.findByUserName(username));
		RegisterEntity user1 = user.get();
		return user1;
		
	}


}
