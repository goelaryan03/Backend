package com.chatApp.backend.service;

import java.util.List;

import com.chatApp.backend.exception.ChatException;
import com.chatApp.backend.exception.UserException;
import com.chatApp.backend.model.Chat;
import com.chatApp.backend.model.User;
import com.chatApp.backend.request.GroupChatRequest;

public interface ChatService  {
	
	public Chat createChat (User reqUser,Integer userId2) throws UserException;
	public Chat findChatById(Integer Id) throws ChatException;
	public List<Chat> findAllChatByUserId(Integer userId) throws UserException;
	public Chat createGroup(GroupChatRequest req, User reqUser) throws UserException;
	public Chat addUserToGroup(Integer userId,Integer chatId,User reqUser) throws UserException,ChatException;
	public Chat renameGroup(Integer chatId,String groupName, User reqUserId) throws ChatException, UserException;
	public Chat removeFromGroup(Integer chatId,Integer userId,User reqUser) throws ChatException,UserException;
	public void deleteChat(Integer chatId,Integer userId) throws ChatException,UserException;
	
}
