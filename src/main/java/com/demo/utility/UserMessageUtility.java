package com.demo.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.demo.model.UserMessage;
import com.demo.model.UserMessageDetail;

public class UserMessageUtility {

	int hops = 0;
	Long previousMinParentMsgId = -1L;

	boolean parentfound = false;
	boolean messageAdded = false;
	boolean mapCreated = false;

	Map<Integer, UserMessage> hopMap = new HashMap<Integer, UserMessage>();
	Map<Long, UserMessage> userMessageMap = new HashMap<Long, UserMessage>();

	List<UserMessageDetail> displayMessageList = new ArrayList<UserMessageDetail>();

	public List<UserMessageDetail> getUserMessageDetailTree(
			List<UserMessage> userMessageList) {
		Map<Long, List<UserMessageDetail>> userDetailMap = new TreeMap<Long, List<UserMessageDetail>>();
		Long parentMsgId = userMessageList.get(0).getParentMessageId();

		for (UserMessage userMsg : userMessageList) {
			if (userMsg.getParentMessageId() > previousMinParentMsgId) {
				parentMsgId = userMsg.getParentMessageId() < parentMsgId ? userMsg.getParentMessageId() : parentMsgId;
			}
		}

		UserMessage rootUserMessage = null;

		// create map of all messages.
		if (userMessageMap.isEmpty()) {
			for (int counter = 0; counter < userMessageList.size(); counter++) {
				userMessageMap.put(((UserMessage) userMessageList.get(counter))
						.getMessageId(), (UserMessage) userMessageList
						.get(counter));
			}
		}

		for (int counter = 0; counter < userMessageList.size(); counter++) {
			UserMessage userMessage = userMessageList.get(counter);
			int hopCount = 0;

			List<UserMessageDetail> tempDetailList = new ArrayList<UserMessageDetail>();

			if (userMessage.getParentMessageId() == 0) {
				tempDetailList.add(new UserMessageDetail(userMessage, 0));
				userDetailMap.put(userMessage.getMessageId(), tempDetailList);
				rootUserMessage = userMessage;
			} else {
				// Call Method to get hop count for this user message
				Map<Integer, UserMessage> hopCountMap = getHopcount(
						userMessage, userMessageMap);

				for (Entry<Integer, UserMessage> hopMessage : hopCountMap
						.entrySet()) {
					hopCount = (int) hopMessage.getKey();
					rootUserMessage = hopMessage.getValue();
				}
				hops = 0;
				hopMap = new HashMap<Integer, UserMessage>();

				ArrayList<UserMessageDetail> tempList = new ArrayList<UserMessageDetail>();

				if (userDetailMap.get(rootUserMessage.getMessageId()) != null) {
					tempList = ((ArrayList<UserMessageDetail>) userDetailMap
							.get(rootUserMessage.getMessageId()));
					tempList.add(new UserMessageDetail(userMessage, hopCount));
				} else {
					tempList.add(new UserMessageDetail(userMessage, hopCount));
				}

				userDetailMap.put(rootUserMessage.getMessageId(), tempList);
			}
		}

		//Reset previous parent ID to current
		previousMinParentMsgId = parentMsgId;

		// call method to return final list
		List<UserMessageDetail> finalUserMessageList = getMessageListWithHops(userDetailMap);
		return finalUserMessageList;
	}

	private List<UserMessageDetail> getMessageListWithHops(
			Map<Long, List<UserMessageDetail>> userDetailMap) {

		for (Entry<Long, List<UserMessageDetail>> usrMessageDetailEntrySet : userDetailMap.entrySet()) {

			List<UserMessage> newUserMessageList = new ArrayList<UserMessage>();
			UserMessageDetail tempUserMessageDetail = new UserMessageDetail();

			for (UserMessageDetail msgDetail : userDetailMap.get(usrMessageDetailEntrySet.getKey())) {
				messageAdded = false;
				tempUserMessageDetail = msgDetail;

				if (msgDetail.getUserMessage().getParentMessageId() != previousMinParentMsgId) {
					newUserMessageList.add(msgDetail.getUserMessage());
				} else {
					displayMessageList.add(msgDetail);
					messageAdded = true;
				}

			}

			if (!newUserMessageList.isEmpty() && newUserMessageList.size() > 1) {
				mapCreated = true;
				getUserMessageDetailTree(newUserMessageList);

			} else {
				if (!messageAdded && newUserMessageList.size() == 1) {
					displayMessageList.add(tempUserMessageDetail);
				}
				tempUserMessageDetail = new UserMessageDetail();
			}
		}
		return displayMessageList;
	}

	private Map<Integer, UserMessage> getHopcount(UserMessage userMessage,
			Map<Long, UserMessage> userMessageMap) {

		if (userMessage != null && userMessage.getParentMessageId() != 0 && userMessageMap.get(userMessage.getParentMessageId()) != null) {
			hops++;
			Map<Integer, UserMessage> mapTest = getHopcount(
					userMessageMap.get(userMessage.getParentMessageId()),
					userMessageMap);

			if (parentfound){
				return mapTest;
			}
		} else {
			parentfound = true;
		}
		hopMap.put(hops, userMessage);
		return hopMap;
	}

}
