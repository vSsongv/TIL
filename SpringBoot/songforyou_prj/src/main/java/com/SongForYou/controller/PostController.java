package com.SongForYou.controller;

import com.SongForYou.dto.PostDto;
import com.SongForYou.dto.PostReadDto;
import com.SongForYou.entity.Post;
import com.SongForYou.repository.PostRepository;
import com.SongForYou.service.MemberService;
import com.SongForYou.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping(value = "/new")
    public String postForm(Model model) {
        model.addAttribute("postDto",new PostDto());
        return "post/form";
    }

    @GetMapping(value = "/{postId}")
    public String detailPost(Model model,  @PathVariable("postId") Long postId) {
        PostReadDto postReadDto = postService.readPost(postId);
        model.addAttribute("postReadDto", postReadDto);
        return "post/detail";
    }

    @GetMapping(value = "/{postId}/update")
    public String getPost(Model model, @PathVariable("postId") Long postId) {
        Post post = postService.findPost(postId);
        model.addAttribute("postDto", post);
        return "post/form";
    }

    @PostMapping(value = "/new")
    public String uploadPost(@Valid PostDto postDto, BindingResult bindingResult,
                          Model model, Principal principal) {
        if(bindingResult.hasErrors()){
            return "post/form";
        }
        Long memberId = memberService.findByEmail(principal.getName());
        try {
            Long postId = postService.createPost(postDto, memberId);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", "게시글 등록 중 에러가 발생하였습니다.");
            return "post/form";
        }
        return "redirect:/";
    }

    @PostMapping(value = "/{postId}/update")
    public String updatePost(@Valid PostDto postDto, Model model, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "post/form";
        }
        try {
            System.out.println(postDto);
            postService.updatedPost(postDto);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", "게시글 등록 중 에러가 발생하였습니다.");
            return "post/form";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return "redirect:/";
    }
}

