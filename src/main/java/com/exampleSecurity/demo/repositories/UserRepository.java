package com.exampleSecurity.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exampleSecurity.demo.entities.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{
	
	UserModel findByUsername(String username);

}

