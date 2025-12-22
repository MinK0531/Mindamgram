package com.mink.mindamgram.comment.service;

import com.mink.mindamgram.comment.domain.Comment;
import com.mink.mindamgram.comment.dto.CommentDetail;
import com.mink.mindamgram.comment.repository.CommentRepository;
import com.mink.mindamgram.user.domain.User;
import com.mink.mindamgram.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
//    public CommentService(CommentRepository commentRepository){
//        this.commentRepository =commentRepository;
//    }

    public List<CommentDetail> getCommentList(long postId) {
        List<Comment> commentList = commentRepository.findByPostId(postId);
        List<CommentDetail> commentDetailList = new ArrayList<>();
        for (Comment comment : commentList) {
            User user = userService.getUserById(comment.getUserId());
            CommentDetail commentDetail = CommentDetail.builder()
                    .id(comment.getId())
                    .userId(comment.getUserId())
                    .userId(comment.getUserId())
                    .signinId(user.getSigninId())
                    .comments(comment.getComments())
                    .build();
            commentDetailList.add(commentDetail);
        }
        return commentDetailList;
    }

    public boolean createComment (long postId, long userId, String comments) {

        Comment comment = Comment.builder()
                .postId(postId)
                .userId(userId)
                .comments(comments)
                .build();
        try{
            commentRepository.save(comment);
        }catch (DataAccessException e){
            return false;
        }
        return true;
    }
}
