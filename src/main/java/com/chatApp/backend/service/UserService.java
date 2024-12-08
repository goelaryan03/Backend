package com.chatApp.backend.service;

import java.util.List;

import com.chatApp.backend.exception.UserException;
import com.chatApp.backend.model.User;
import com.chatApp.backend.request.UpdateUserRequest;

public interface UserService {
 public User findUserById(Integer Id) throws UserException;
 public User findUserProfile(String jwt) throws UserException;
 public User updateUser(Integer userId, UpdateUserRequest req) throws UserException;
 public List<User> searchUser(String query);
 
}
