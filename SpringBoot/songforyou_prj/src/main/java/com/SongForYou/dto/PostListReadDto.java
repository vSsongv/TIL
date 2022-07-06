package com.SongForYou.dto;

import com.SongForYou.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostListReadDto {
    private String title;
    private Long postId;
    private String writer;

    public PostListReadDto(Post post){
        this.title = post.getTitle();
        this.postId = post.getId();
        this.writer = post.getMember().getNickName();
    }
}
