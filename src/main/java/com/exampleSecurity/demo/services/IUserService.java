package com.exampleSecurity.demo.services;
import com.exampleSecurity.demo.entities.UserModel;
import org.springframework.stereotype.Service;


public interface IUserService {

    UserModel save(UserModel userModel);
    UserModel findByUsername(String username);

}
