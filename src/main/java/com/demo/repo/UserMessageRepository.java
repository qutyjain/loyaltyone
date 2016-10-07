package com.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.model.UserMessage;

public interface UserMessageRepository extends JpaRepository<UserMessage,String> {

}
