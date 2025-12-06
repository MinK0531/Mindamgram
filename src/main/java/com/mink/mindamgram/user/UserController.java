package com.mink.mindamgram.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

    @GetMapping("/signup")
    public String signup(){
        return "/user/signup";
    }

    @GetMapping("/signin")
    public String sigin(){
        return "/user/signin";
    }

    @GetMapping("/home")
    public String home(){
        return "/user/home";
    }
}
