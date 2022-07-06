package com.SongForYou.dto;

import com.SongForYou.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentReadDto {
    private String content;
    private Long comment_id;
    private String writer;
    private Long writer_id;
    private Long post_id;

    public CommentReadDto(Comment comment){
        content = comment.getContent();
        comment_id = comment.getId();
        writer_id = comment.getMember().getId();
        writer = comment.getMember().getNickName();
        post_id = comment.getPost().getId();
    }

}
