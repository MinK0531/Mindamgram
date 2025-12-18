package com.mink.mindamgram.post.service;

import com.mink.mindamgram.common.FileManager;
import com.mink.mindamgram.like.service.LikeService;
import com.mink.mindamgram.post.domain.Post;
import com.mink.mindamgram.post.dto.PostDetail;
import com.mink.mindamgram.post.repository.PostRepository;
import com.mink.mindamgram.user.domain.User;
import com.mink.mindamgram.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor //필수 멤버변수를 생성자를 통해 대응
@Service
public class PostService {
    //넘 많음 - 도메인에 포함된
    //생성자에 대해 잘 알면 사용하렴- 난 제외^^

    private final PostRepository postRepository;
    private final UserService userService;
    private  final LikeService likeService;
    //
//    public PostService(PostRepository postRepository, UserService userService,LikeService likeService){
//        this.postRepository = postRepository;
//        this.userService = userService;
//        this.likeService=likeService;
//    }

    public boolean createPost(
            long userId,
            String contents,
            MultipartFile imageFile){
        String imagePath = FileManager.saveFile(userId,imageFile);

        Post post = Post.builder().
                userId(userId).
                contents(contents).
                imagePath(imagePath).
                build();
        try {
            postRepository.save(post);
        }catch (DataAccessException e){
            return false;
        }
        return true;
    }
    public List<PostDetail> getPostList(long userId) {

        List<Post> postList = postRepository.findAll(Sort.by("id").descending());

        List<PostDetail> postDetailList = new ArrayList<>();
        for(Post post:postList) {
            // Post -> PostDetail
            // 1 + N 문제  : cache
            User user = userService.getUserById(post.getUserId());

            int likeCount = likeService.countByPostId(post.getId());
            boolean isLike = likeService.isLikeByPostIdAndUserId(post.getId(), userId);

            PostDetail postDetail = PostDetail.builder()
                    .id(post.getId())
                    .contents(post.getContents())
                    .imagePath(post.getImagePath())
                    .userId(post.getUserId())
                    .signinId(user.getSigninId())
                    .likeCount(likeCount)
                    .isLike(isLike)
                    .build();
            postDetailList.add(postDetail);
        }

        return postDetailList;
    }
    public Post getPost(long id){
        Optional<Post> optionalPost =postRepository.findById(id);
        return optionalPost.get();
    }
    public List<Post> getPostListProfile(long userId) {
        return postRepository.findByUserId(userId, Sort.by("id").descending());
    }
}
