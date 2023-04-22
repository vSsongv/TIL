package com.SongForYou.service;


import com.SongForYou.dto.CommentReadDto;
import com.SongForYou.entity.Comment;
import com.SongForYou.entity.Member;
import com.SongForYou.repository.CommentRepository;
import com.SongForYou.repository.MemberRepository;
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
    private final MemberRepository memberRepository;
    @Transactional
    public Long createComment(Comment comment, Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("not found member"));
        comment.setMember(member);
        Long commentId = commentRepository.save(comment).getId();
        return commentId;
    }

    @Transactional
    public Long updateComment(Long commentId, Comment comment){
        Comment updatedComment = commentRepository.findById(commentId)
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

    public Comment findComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("not found comment"));
        return comment;
    }
}