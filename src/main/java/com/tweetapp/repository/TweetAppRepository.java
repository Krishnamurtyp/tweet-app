package com.tweetapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.entity.RegisterEntity;


@Repository
public interface TweetAppRepository extends MongoRepository<RegisterEntity, String> {
	public RegisterEntity findByUserName(String username);
}
