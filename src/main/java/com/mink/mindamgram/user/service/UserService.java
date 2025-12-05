package com.mink.mindamgram.user.service;

import com.mink.mindamgram.common.MD5HashingEncoder;
import com.mink.mindamgram.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public boolean createUser(
            String loginId
            , String password
            , String name
            , String email){
        String encodedPassword = MD5HashingEncoder.encode(password);

        int count = userRepository.insertUser(loginId, encodedPassword, name, email);
        if(count == 1){
            return true;
        }else{
            return false;
        }
    }

}
