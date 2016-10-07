package com.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="USER_ACCOUNT")
public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE1")
	@SequenceGenerator(name = "SEQUENCE1", sequenceName = "USER_NAME_SEQ", allocationSize = 1)
	Long userId;

	@Column
	private String userName;

	@OneToMany(mappedBy = "userAccount")
	private List<UserMessage> userMessageList;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<UserMessage> getUserMessageList() {
		return userMessageList;
	}

	public void setUserMessageList(List<UserMessage> userMessageList) {
		this.userMessageList = userMessageList;
	}

}
