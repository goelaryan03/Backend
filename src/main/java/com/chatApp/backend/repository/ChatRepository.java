package com.chatApp.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatApp.backend.model.Chat;
import com.chatApp.backend.model.User;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
	
	@Query("select c from Chat c join c.users u where u.id=:userId")
	public List<Chat> findAllChatsOfUser(@Param("userId")Integer reqUserId);
	
   @Query("select c from Chat c where c.isGroup=false And :user Member of c.users And %:reqUser% Member of c.users")
   public Chat findChatByUserIds(@Param("user")User user,@Param("reqUser")User reqUser);
}
