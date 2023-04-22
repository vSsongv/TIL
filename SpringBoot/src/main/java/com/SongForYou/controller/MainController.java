package com.SongForYou.controller;

import com.SongForYou.dto.PostListReadDto;
import com.SongForYou.entity.Post;
import com.SongForYou.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    public final PostService postService;

    @GetMapping(value = "/")
    public String main(Model model) {

        List<Post> posts = postService.postList();
        model.addAttribute("posts", posts);
        return "main";
    }
}
