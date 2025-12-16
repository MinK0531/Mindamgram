package com.mink.mindamgram.post;

import com.mink.mindamgram.post.domain.Post;
import com.mink.mindamgram.post.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/post")
@RestController
public class PostRestController {

    private final PostService postService;
    public PostRestController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("write-process")
    public Map<String,String> write(
            @RequestParam String contents,
            @RequestParam(required = false)MultipartFile imageFile,
            HttpSession session){

        long userId = (Long)session.getAttribute("userId");

        Map<String, String> resultMap = new HashMap<>();
        if(postService.createPost(userId,contents,imageFile)){
            resultMap.put("result", "success");
        }else {
            resultMap.put("result", "fail");
        }
        return resultMap;
    }

}
