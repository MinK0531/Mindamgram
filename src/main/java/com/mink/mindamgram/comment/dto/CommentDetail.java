package com.mink.mindamgram.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentDetail {
    private long id;
    private long userId;
    private String comments;
    private String signinId;
}