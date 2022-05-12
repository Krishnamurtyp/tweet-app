package com.tweetapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.entity.ForgotPasswordEntity;
import com.tweetapp.entity.JwtUtil;
import com.tweetapp.entity.RegisterEntity;
import com.tweetapp.entity.TweetPostEntity;
import com.tweetapp.entity.TweetPostReplyEntity;
import com.tweetapp.exception.InvalidPasswordException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.CustomErrorReponse;
import com.tweetapp.request.AuthRequest;
import com.tweetapp.request.LoginUserRequest;
import com.tweetapp.request.RegisterUserRequest;
import com.tweetapp.request.TweetUpdateRequest;
import com.tweetapp.request.UserNewTweetRequest;
import com.tweetapp.request.UserReplyTweetRequest;
import com.tweetapp.response.LoginUserResponse;
import com.tweetapp.response.RegisterUserResponse;
import com.tweetapp.response.UserNewTweetResponse;
import com.tweetapp.response.UserReplyTweetResponse;
import com.tweetapp.services.TweetappServicesImpl;
import com.tweetapp.services.UserDetailsServiceImpl;


@RestController
@RequestMapping("/api/v1.0/tweets")
@CrossOrigin(origins = {"*"})
public class TweetAppController {

	@Autowired
	TweetappServicesImpl tweetappServicesImpl;

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/register")
	public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
		try {
		

			return new ResponseEntity<>(tweetappServicesImpl.createUser(registerUserRequest), HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
	try {
	authenticationManager.authenticate(
	new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
	);
	} catch (InvalidPasswordException ex) {
	throw new InvalidPasswordException("inavalid username/password");
	}
	return jwtUtil.generateToken(authRequest.getUserName());
	}


	@GetMapping("/all")
	public ResponseEntity<List<TweetPostEntity>> getAllTweet() {
		try {
			return new ResponseEntity<>(tweetappServicesImpl.getAllTweet(), HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("{username}/all")
	public ResponseEntity<List<TweetPostEntity>> getAllTweet(@PathVariable String username) {
		try {
			return new ResponseEntity<>(tweetappServicesImpl.viewAllMyTweets(username), HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/users/all")
	public ResponseEntity<List<RegisterEntity>> getAllUsers() {
		try {
			return new ResponseEntity<>(userDetailsServiceImpl.getUserList(), HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{username}")
	public ResponseEntity<RegisterEntity> getUserDetails(@PathVariable String username) {
		try {
			return new ResponseEntity<>(userDetailsServiceImpl.getUserDetails(username), HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/{username}/add")
	public ResponseEntity<UserNewTweetResponse> postNewTweet(@Valid @RequestBody UserNewTweetRequest userNewTweetRequest,
			@PathVariable String username) {
		try {
			userNewTweetRequest.setUserName(username);
			return new ResponseEntity<>(tweetappServicesImpl.createUserTweet(userNewTweetRequest), HttpStatus.OK);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/reply/{tweetId}")
	public ResponseEntity<UserReplyTweetResponse> postReplyTweet(
			@RequestBody UserReplyTweetRequest userReplyTweetRequest, @PathVariable String tweetId) {
		try {
			userReplyTweetRequest.setTweetId(tweetId);
			return new ResponseEntity<>(tweetappServicesImpl.createUserReplyTweet(userReplyTweetRequest),
					HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("updateTweet")
	public ResponseEntity<UserNewTweetResponse> updatePostedTweet(@RequestBody TweetUpdateRequest tweetUpdateRequest) {
		try {
			return new ResponseEntity<>(tweetappServicesImpl.updateUserTweet(tweetUpdateRequest), HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("delete/{tweetId}")
	public ResponseEntity<CustomErrorReponse> deleteUserTweet(@PathVariable String tweetId) {
		CustomErrorReponse response = new CustomErrorReponse();
		try {
			tweetappServicesImpl.deleteUserTweet(tweetId);
			response.setMessage("Successfully deleted the tweet");
			return new ResponseEntity<CustomErrorReponse>(response, HttpStatus.OK);
		} catch (Exception exception) {
			response.setMessage("Unable to delete the tweet");
			return new ResponseEntity<CustomErrorReponse>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{username}/like/{id}")
	public ResponseEntity<UserNewTweetResponse> likeaTweet(@PathVariable String username, @PathVariable String id,
			@RequestBody TweetPostEntity tweetPostEntity) {

		try {

			return new ResponseEntity<>(tweetappServicesImpl.likeaTweet(tweetPostEntity), HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("reply/{tweetId}")
	public ResponseEntity<List<TweetPostReplyEntity>> getAllReplyTweets(@PathVariable String tweetId) {
		try {
			return new ResponseEntity<>(tweetappServicesImpl.getAllReplyTweets(tweetId), HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("forgotPassword")
	public ResponseEntity<CustomErrorReponse> forgotPassword(@RequestBody ForgotPasswordEntity forgotPasswordEntity) {
		LoginUserRequest loginUserRequest = new LoginUserRequest();
		loginUserRequest.setUserName(forgotPasswordEntity.getUsername());
		UserDetails user = null;
		try {
			user = userDetailsServiceImpl.loadUserByUsername(loginUserRequest.getUserName());
		} catch (Exception e) {
			return new ResponseEntity<CustomErrorReponse>(new CustomErrorReponse("User Not Found"),
					HttpStatus.NOT_FOUND);
		}
		loginUserRequest.setPassword(forgotPasswordEntity.getPassword());
		if (tweetappServicesImpl.forgotPassword(loginUserRequest, forgotPasswordEntity.getEmailId())) {
			return new ResponseEntity<CustomErrorReponse>(new CustomErrorReponse("Password Set Successfully"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<CustomErrorReponse>(
					new CustomErrorReponse("Please Enter correct userName and Email ID"), HttpStatus.BAD_REQUEST);
			
		}

	}
}
