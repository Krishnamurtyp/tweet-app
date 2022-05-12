package com.tweetapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.entity.TweetPostReplyEntity;

public interface TweetPostReplyRepository extends MongoRepository<TweetPostReplyEntity, String> {

}
