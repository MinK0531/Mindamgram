package com.mink.mindamgram.post;
import com.mink.mindamgram.post.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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




}
