package com.demo.model;

public class UserMessageDetail {

	public UserMessageDetail(UserMessage userMessage, int hopCountToRoot) {
		super();
		this.userMessage = userMessage;
		this.hopCountToRoot = hopCountToRoot;
	}

	public UserMessageDetail() {

	}

	UserMessage userMessage;
	int hopCountToRoot;

	public UserMessage getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(UserMessage userMessage) {
		this.userMessage = userMessage;
	}

	public int getHopCountToRoot() {
		return hopCountToRoot;
	}

	public void setHopCountToRoot(int hopCountToRoot) {
		this.hopCountToRoot = hopCountToRoot;
	}
}
