package com.chatApp.backend.request;

import java.util.List;

public class GroupChatRequest {

	private String group_name;
	private String group_icon;
	private List<Integer> userIds;
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getGroup_icon() {
		return group_icon;
	}
	public void setGroup_icon(String group_icon) {
		this.group_icon = group_icon;
	}
	public List<Integer> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Integer> userIds) {
		this.userIds = userIds;
	}
	public GroupChatRequest(String group_name, String group_icon, List<Integer> userIds) {
		super();
		this.group_name = group_name;
		this.group_icon = group_icon;
		this.userIds = userIds;
	}
	public GroupChatRequest() {
		// TODO Auto-generated constructor stub
	}
}
