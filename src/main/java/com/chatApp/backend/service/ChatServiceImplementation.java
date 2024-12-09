package com.chatApp.backend.service;

import java.util.List;
import java.util.Optional;

import com.chatApp.backend.exception.ChatException;
import com.chatApp.backend.exception.UserException;
import com.chatApp.backend.model.Chat;
import com.chatApp.backend.model.User;
import com.chatApp.backend.repository.ChatRepository;
import com.chatApp.backend.repository.UserRepository;
import com.chatApp.backend.request.GroupChatRequest;

public class ChatServiceImplementation implements ChatService {
	private ChatRepository chatRepository;
	private UserRepository userRepository;
	private UserService userService;
	
	public ChatServiceImplementation(UserRepository userRepository,ChatRepository chatRepository,UserService userService) {
		this.chatRepository=chatRepository;
		this.userRepository=userRepository;
		this.userService=userService;
	}

	@Override
	public Chat createChat(User reqUser, Integer userId2) throws UserException {
		User user=userService.findUserById(userId2);
		
		Chat isChatExist=chatRepository.findChatByUserIds(user, reqUser);
		if(isChatExist!=null) {
			return isChatExist;
		}
		Chat chat = new Chat();
		chat.setCreatedBy(reqUser);
		chat.getUsers().add(reqUser);
		chat.getUsers().add(user);
		chat.setGroup(false);
		
		return chat;
	}

	@Override
	public Chat findChatById(Integer chatId) throws ChatException {
		Optional<Chat> chat= chatRepository.findById(chatId);
		if(chat!=null) {
			return chat.get();
		}
		
		throw new ChatException("No chat found with ID: "+chatId);
	}

	@Override
	public List<Chat> findAllChatByUserId(Integer userId) throws UserException {
		List<Chat> chats=chatRepository.findAllChatsOfUser(userId);
		return chats;
	}

	@Override
	public Chat createGroup(GroupChatRequest req, User reqUser) throws UserException {
		Chat group=new Chat();
		group.setGroup(true);
		group.setChat_image(req.getGroup_icon());
		group.setChat_name(req.getGroup_name());
		group.setCreatedBy(reqUser);
		group.getAdmins().add(reqUser);
		for(Integer userId:req.getUserIds()) {
			User user=userService.findUserById(userId);
			group.getUsers().add(user);
		}
		
		return group;
	}

	@Override
	public Chat addUserToGroup(Integer userId, Integer chatId,User reqUser) throws UserException, ChatException {

		Optional<Chat> opt=chatRepository.findById(chatId);
		User user=userService.findUserById(userId);
		if(opt.isPresent()) {
			Chat group=opt.get();
			if(group.getAdmins().contains(reqUser)) {
				opt.get().getUsers().add(user);
				return group;
			}
			else {
				throw new UserException("You do not have admin access for group "+chatId);
			}
			
		}
		throw new ChatException("No group found with Id"+chatId);
	}

	@Override
	public Chat renameGroup(Integer chatId, String groupName, User reqUser) throws ChatException, UserException {
		Optional<Chat> opt=chatRepository.findById(chatId);
		if(opt.isPresent()) {
			Chat group=opt.get();
			if(group.getUsers().contains(reqUser)) {
				group.setChat_name(groupName);
				return chatRepository.save(group);
			}
			else {
				throw new UserException("You are not a member of group "+chatId);
			}
		}
		
		
		throw new ChatException("Group not found with ID "+chatId);
	}

	@Override
	public Chat removeFromGroup(Integer chatId, Integer userId, User reqUser) throws ChatException, UserException {
		Optional<Chat> opt=chatRepository.findById(chatId);
		User user=userService.findUserById(userId);
		if(opt.isPresent()) {
			Chat group=opt.get();
			if(group.getAdmins().contains(reqUser)) {
				group.getUsers().remove(user);
				return group;
			}
			else if(group.getUsers().contains(reqUser)) {
				if(reqUser.getId().equals(userId)) {
					group.getUsers().remove(user);
					return group;
				} 
			}
			else {
				throw new UserException("You do not have admin access for group "+chatId);
			}
			
		}
		throw new ChatException("No group found with Id"+chatId);
	}

	@Override
	public void deleteChat(Integer chatId, Integer userId) throws ChatException, UserException {
		Optional<Chat> opt=chatRepository.findById(chatId);
		if(opt.isPresent()) {
			Chat chat=opt.get();
			chatRepository.deleteById(chat.getId());
			
		}
		throw new ChatException("No group found with Id"+chatId);
	}

	
	
}
