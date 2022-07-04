package com.songforyou.repository;

import com.songforyou.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Integer countByPostId(Long id);
    List<Comment> findAllByMemberId(Long id);
    List<Comment> findAllByPostId(Long id);
}
