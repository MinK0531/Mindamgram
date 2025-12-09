package com.mink.mindamgram.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

    @GetMapping("/signup")
    public String signup(){
        return "user/signup";
    }

    @GetMapping("/signin")
    public String sigin(){
        return "user/signin";
    }
    @GetMapping("/signout")
    public String signout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/user/signin";
    }


}
