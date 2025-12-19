package com.mink.mindamgram.comment.repository;

import com.mink.mindamgram.comment.domain.Comment;
import com.mink.mindamgram.post.domain.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByPostId(long postId, Sort sort);

}
