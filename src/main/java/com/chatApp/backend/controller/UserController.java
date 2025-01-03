package com.chatApp.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatApp.backend.exception.UserException;
import com.chatApp.backend.model.User;
import com.chatApp.backend.request.UpdateUserRequest;
import com.chatApp.backend.response.ApiResponse;
import com.chatApp.backend.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String token) throws UserException {
		User user = userService.findUserProfile(token);
		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{query}")
	public ResponseEntity<List<User>> searchUserHandler(@PathVariable("query") String q) {
		List<User> users = userService.searchUser(q);
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<ApiResponse>updateUserHandler(@RequestBody UpdateUserRequest req, @RequestHeader("Authorization") String token) throws UserException{
		User user = userService.findUserProfile(token);
		userService.updateUser(user.getId(), req);
		ApiResponse res= new ApiResponse("User Updated Sucessfully", true);
		return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);
	}
}
