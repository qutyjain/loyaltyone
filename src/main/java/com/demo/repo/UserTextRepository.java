package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.model.UserText;

public interface UserTextRepository extends JpaRepository<UserText,String> {

}
