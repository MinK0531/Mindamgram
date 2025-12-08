package com.mink.mindamgram.user;

import com.mink.mindamgram.user.service.UserService;
import org.springframework.web.bind.annotation.*;

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
    public Map<String,String> login(
            @RequestParam String loginId,
            @RequestParam String password){
        // 사용자 정보 얻어오기
    }

}
