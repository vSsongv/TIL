package com.SongForYou.controller;

import com.SongForYou.dto.PostDto;
import com.SongForYou.repository.PostRepository;
import com.SongForYou.service.MemberService;
import com.SongForYou.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping(value = "/new")
    public String writeNewPost(Model model) {
        model.addAttribute("postDto",new PostDto());
        return "post/new";
    }

//    @PostMapping(value = "/new")
//    public String newPost(@Valid PostDto postDto, BindingResult bindingResult,
//                          Model model, Principal principal) {
//        if(bindingResult.hasErrors()){
//            return "post/new";
//        }
//        Long memberId = memberService.findByEmail(principal.getName());
//        try {
//            Long postId = postService.createPost(postDto, memberId);
//        } catch (IllegalStateException e){
//            model.addAttribute("errorMessage", "게시글 등록 중 에러가 발생하였습니다.");
//            return "post/new";
//        }
//        return "redirect:/";
//    }
}
