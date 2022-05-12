package com.tweetapp.entity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("kafka")
public class Producer {
		
	    private static final String TOPIC = "TweetCompTwo";
	    
	    @Autowired
	    public KafkaTemplate<String, String> kafkaTemplate;
	    

	    public void sendMessage(String message) {
	        this.kafkaTemplate.send(TOPIC, message);
	    }
	  
}
