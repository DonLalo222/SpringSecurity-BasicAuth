package com.exampleSecurity.demo.services;

import com.exampleSecurity.demo.entities.UserModel;
import com.exampleSecurity.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository repository;

    @Override
    public UserModel save(UserModel userModel) {

        if(userModel != null){
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            userModel.setEnabled(true);
            userModel.setRole("USER");
            userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));

            repository.save(userModel);

            return userModel;
        }
        return null;
    }

    @Override
    public UserModel findByUsername(String username) {
        if(!username.trim().isEmpty()){
            return repository.findByUsername(username);
        }
        return null;
    }
}
