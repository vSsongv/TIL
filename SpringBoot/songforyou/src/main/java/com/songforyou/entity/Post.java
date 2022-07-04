package com.songforyou.entity;

import com.songforyou.dto.PostDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "post")
@ToString
@Builder
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Long id;

    private String title;
    @Column(length=100)
    private String content;
    private String link;
    private int genreId;

    //@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "post")
    //List<Comment> comments = new ArrayList<>();

//    public static Post createPost(PostDto postDto){
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setContent(postDto.getContent());
//        post.setGenreId(postDto.getGenreId());
//        return post;
//    }
}
