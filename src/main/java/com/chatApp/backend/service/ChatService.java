package com.chatApp.backend.service;

import com.chatApp.backend.exception.UserException;
import com.chatApp.backend.model.Chat;

public interface ChatService  {
	
	public Chat createChat (Integer reqUser,Integer userId2) throws UserException; 

}
