package com.SongForYou.dto;

import com.SongForYou.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class PostReadDto {
    private String title;
    private String content;
    private String link;
    private Integer genreId;
    private String writer;
    private Long writerId;
    private Long postId;

    public LocalDateTime createdTime;
//    private Integer numOfComment;
//    private List<CommentReadDto> commentReadDtoList;

//    public PostReadDto(Post post,Integer numOfComment, List<CommentReadDto> commentReadDtoList){
    public PostReadDto(Post post){
        this.title = post.getTitle();
        this.content = post.getContent();
        this.link = post.getLink();
        this.genreId = post.getGenreId();
        this.writer = post.getMember().getNickName();
        this.writerId = post.getMember().getId();
        this.postId = post.getId();
        this.createdTime = post.getCreatedTime();
//        this.numOfComment = numOfComment;
//        this.commentReadDtoList = commentReadDtoList;
    }
}
