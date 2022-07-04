package com.songforyou.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PostDto {
    private String title;
    private String content;
    private String link;
    private Integer genreId;
//    private Integer countedComments;
//    private List<CommentReadDto> comments;

}
