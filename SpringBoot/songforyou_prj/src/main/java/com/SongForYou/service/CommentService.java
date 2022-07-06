package com.SongForYou.service;


import com.SongForYou.dto.CommentReadDto;
import com.SongForYou.entity.Comment;
import com.SongForYou.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Long createComment(Comment comment){
        Long commentId = commentRepository.save(comment).getId();
        return commentId;
    }

    @Transactional
    public Long updateComment(Long id, Comment comment){
        Comment updatedComment = commentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("don't exist"));
        comment.setContent(comment.getContent());
        return updatedComment.getId();
    }

    @Transactional
    public void deleteComment(Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("don't exitst"));
        commentRepository.delete(comment);
    }

    public List<Comment> findCommentsByMemberId(Long userId){
        return commentRepository.findAllByMemberId(userId);
    }
    public List<Comment> findCommentsByPostId(Long postId){
        return commentRepository.findAllByPostId(postId);
    }

    public List<CommentReadDto> readComment(List<Comment> comments){
        List<CommentReadDto> collect = comments.stream()
                .map(m -> new CommentReadDto(m))
                .collect(Collectors.toList());
        return collect;
    }
}