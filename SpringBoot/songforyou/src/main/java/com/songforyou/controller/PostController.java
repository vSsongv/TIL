package com.songforyou.controller;

import com.songforyou.dto.PostDto;
import com.songforyou.entity.Post;
import com.songforyou.repository.PostRepository;
import com.songforyou.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final PostRepository postRepository;

    @GetMapping(value = "/new")
    public String writeNewPost(Model model) {
        model.addAttribute("postDto",new PostDto());
        return "post/new";
    }

    @PostMapping(value = "/new")
    public String newPost(@Valid PostDto postDto, BindingResult bindingResult,
                          Model model) {
        if(bindingResult.hasErrors()){
            return "post/new";
        }
        Long postId;
        try {
            System.out.println("dsfdfdsf");
            postId = postService.createPost(postDto);
        } catch (Exception e){
            model.addAttribute("errorMessage", "게시글 등록 중 에러가 발생하였습니다.");
            return "post/new";
        }
        return "redirect:/";
    }
}
