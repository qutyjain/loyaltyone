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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserMessage other = (UserMessage) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

}
