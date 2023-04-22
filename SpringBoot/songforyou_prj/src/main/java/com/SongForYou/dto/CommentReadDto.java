package com.SongForYou.dto;

import com.SongForYou.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class CommentReadDto {
    private String content;

    private String email;
    private Long comment_id;
    private String writer;
    private Long writer_id;
    private Long post_id;

    private LocalDateTime createdTime;

    public CommentReadDto(Comment comment){
        content = comment.getContent();
        comment_id = comment.getId();
        writer_id = comment.getMember().getId();
        writer = comment.getMember().getNickName();
        email = comment.getMember().getEmail();
        createdTime = comment.getCreatedTime();
        post_id = comment.getPost().getId();
    }

}
