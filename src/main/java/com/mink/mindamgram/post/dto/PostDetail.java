package com.mink.mindamgram.post.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostDetail {

    private long id;

    private String contents;
    private String imagePath;

    private long userId;
    private String signinId;

    private int likeCount;


}
