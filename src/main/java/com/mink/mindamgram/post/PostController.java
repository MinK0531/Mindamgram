package com.mink.mindamgram.post;
import com.mink.mindamgram.post.domain.Post;
import com.mink.mindamgram.post.dto.PostDetail;
import com.mink.mindamgram.post.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/post")
@Controller
public class PostController {

    private final PostService postService;

    public  PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/home")
    public String home(){
        return "post/home";
    }
    @GetMapping("/create")
    public String create(){
        return "post/create";
    }

    @GetMapping("/profile")
    public String profile(
            Model model,
            HttpSession session){
        long userId = (Long)session.getAttribute("userId");

        List<Post> postList = postService.getPostListProfile(userId);
        model.addAttribute("postList", postList);
        return "post/profile";
    }



}
