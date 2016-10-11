package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="user_message")
public class UserMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE1")
	@SequenceGenerator(name = "SEQUENCE1", sequenceName = "USER_TEXT_SEQ", allocationSize = 1)
	Long messageId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserAccount userAccount;

	@Column
	private String message;

	@Column
	private Long parentMessageId;

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageid) {
		this.messageId = messageid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public Long getParentMessageId() {
		return parentMessageId;
	}

	public void setParentMessageId(Long parentMessageId) {
		this.parentMessageId = parentMessageId;
	}

}
