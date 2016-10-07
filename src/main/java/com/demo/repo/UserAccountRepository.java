package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
	UserAccount findByUserName(String userName);
}
