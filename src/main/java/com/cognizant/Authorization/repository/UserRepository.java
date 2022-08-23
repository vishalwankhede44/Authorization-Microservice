package com.cognizant.Authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.Authorization.model.User;


public interface UserRepository extends JpaRepository<User,Integer> {
	User findByUsername(String username);
}
