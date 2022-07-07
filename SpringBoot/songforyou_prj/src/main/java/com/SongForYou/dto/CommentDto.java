package com.SongForYou.dto;

import com.SongForYou.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private String content;

    private String email;
    private Long postId;
    private Long writerId;

    private String writerName;
}
