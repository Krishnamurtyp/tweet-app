package com.tweetapp.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.entity.TweetPostEntity;

public interface TweetPostRepository extends MongoRepository<TweetPostEntity, String> {

    public List<TweetPostEntity> findByUserName(String username);
}
