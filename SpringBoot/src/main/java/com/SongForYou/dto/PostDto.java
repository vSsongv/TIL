package com.SongForYou.dto;


import lombok.Data;
import javax.validation.constraints.NotBlank;


@Data
public class PostDto {
    private Long id;
    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;
    @NotBlank(message = "내용은 필수 입력 값입니다")
    private String content;
    private String link;
    private Integer genreId;

}