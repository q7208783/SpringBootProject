package com.zcc.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zcc.model.dao.User;

/**
 * Created by NCP-620 on 2017/7/12.
 */
public interface UserRepository extends JpaRepository<User, String> {
	User findUserByUsernameAndPassword(String username, String password);
	User findUserByUsername(String username);
	User findUserById(Long id);
}
