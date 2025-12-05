package com.mink.mindamgram.user;

import com.mink.mindamgram.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup-process")
    public Map<String, String> singup(
            @RequestParam String loginId
            , @RequestParam String password
            , @RequestParam String name
            , @RequestParam String email){

        Map<String,String> resultMap = new HashMap<>();

        if(userService.createUser(loginId, password, name, email)){
            resultMap.put("result","success");
        }else{
            resultMap.put("result","fail");
        }
        return resultMap;
    }

}
