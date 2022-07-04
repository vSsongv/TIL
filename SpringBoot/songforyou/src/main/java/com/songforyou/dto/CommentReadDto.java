package com.songforyou.dto;


import com.songforyou.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class CommentReadDto {
    private String content;
    private Long comment_id;
    private LocalDateTime date;
    private String writer;
    private Long writer_id;
    private Long post_id;

    public CommentReadDto(Comment comment){
        content = comment.getContent();
        date = comment.getDate();
        comment_id = comment.getId();
        writer_id = comment.getMember().getId();
        writer = comment.getMember().getNickName();
        post_id = comment.getPost().getId();
    }

}
