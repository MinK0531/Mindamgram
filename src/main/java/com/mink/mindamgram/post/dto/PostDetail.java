package com.mink.mindamgram.post.dto;

import com.mink.mindamgram.comment.domain.Comment;
import com.mink.mindamgram.comment.dto.CommentDetail;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PostDetail {

    private long id;

    private String contents;
    private String imagePath;

    private long userId;
    private String signinId;

    private int likeCount;
    private  boolean isLike;

    private List<CommentDetail> commentList;
}
