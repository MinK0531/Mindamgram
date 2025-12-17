package com.mink.mindamgram.like.repository;

import com.mink.mindamgram.like.domain.Like;
import com.mink.mindamgram.like.domain.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeId> {

//    SELECT COUNT(*) FROM `like` WHERE `post_id` =10;
    public int countByPostId(long postId);
}
