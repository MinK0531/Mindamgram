package com.mink.mindamgram.user.service;

import com.mink.mindamgram.common.MD5HashingEncoder;
import com.mink.mindamgram.common.SHA256HashingEncoder;
import com.mink.mindamgram.user.domain.User;
import com.mink.mindamgram.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public boolean createUser(
            String signinId
            , String password
            , String name
            , String email){
        String encodedPassword = SHA256HashingEncoder.encode(password);

        int count = userRepository.insertUser(signinId, encodedPassword, name, email);
        if(count == 1){
            return true;
        }else{
            return false;
        }
    }
    public boolean isDuplicateId(String signinId){
        int count = userRepository.countBySigninId(signinId);
        if(count == 0){
            return false;
        }else {
            return true;
        }
    }
    public User getUser(String signinId, String password){
        String encodededPassword = SHA256HashingEncoder.encode(password);
        User user = userRepository.selectUser(signinId, encodededPassword);
        return user;
    }

}
