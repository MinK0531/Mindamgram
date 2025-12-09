package com.mink.mindamgram.user;

import com.mink.mindamgram.user.domain.User;
import com.mink.mindamgram.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
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
            @RequestParam String signinId
            , @RequestParam String password
            , @RequestParam String name
            , @RequestParam String email){

        Map<String,String> resultMap = new HashMap<>();

        if(userService.createUser(signinId, password, name, email)){
            resultMap.put("result","success");
        }else{
            resultMap.put("result","fail");
        }
        return resultMap;
    }
    @GetMapping("/duplicate-id")
    public Map<String, Boolean> isDuplicateId(@RequestParam String signinId){

        Map<String,Boolean> resultMap = new HashMap<>();

        if(userService.isDuplicateId(signinId)){
            resultMap.put("isDuplicate",true);
        }else{
            resultMap.put("isDuplicate",false);
        }
        return  resultMap;
    }
    @PostMapping("/signin-process")
    public Map<String, String> signin(
            @RequestParam String signinId
            , @RequestParam String password
            , HttpServletRequest request){
        User user = userService.getUser(signinId,password);

        Map<String,String> resultMap = new HashMap<>();

        if(user != null){
            resultMap.put("result", "success");
            HttpSession session = request.getSession();

            session.setAttribute("userId",user.getId());
            session.setAttribute("userName",user.getName());

        }else {
            resultMap.put("result", "fail");
        }

        return resultMap;
    }

}
