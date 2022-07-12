package com.SongForYou.controller;

import com.SongForYou.dto.CommentDto;
import com.SongForYou.dto.PostDto;
import com.SongForYou.dto.PostReadDto;
import com.SongForYou.entity.Member;
import com.SongForYou.entity.Post;
import com.SongForYou.repository.MemberRepository;
import com.SongForYou.repository.PostRepository;
import com.SongForYou.service.MemberService;
import com.SongForYou.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @Autowired
    private final MemberRepository memberRepository;
    @GetMapping(value = "/new")
    public String postForm(Model model) {
        model.addAttribute("postDto",new PostDto());
        return "post/form";
    }

    @GetMapping(value = "/{postId}")
    public String detailPost(Model model,  @PathVariable("postId") Long postId, Principal principal) {
        PostReadDto postReadDto = postService.readPost(postId);
        Optional<Member> member = memberRepository.findById(postReadDto.getWriterId());
        CommentDto commentDto = new CommentDto();

        if (principal != null) { //로그인 정보가 있을 떄
            if (principal.getName().equals(member.get().getEmail())) {
                model.addAttribute("myPost", 1);
            }
            model.addAttribute("userId", principal.getName());
        }

        model.addAttribute("postReadDto", postReadDto);

        model.addAttribute("commentDto",commentDto);
        return "post/detail";
    }

    @GetMapping(value = "/{postId}/update")
    public String getPost(Model model, @PathVariable("postId") Long postId) {
        Post post = postService.findPost(postId);
        model.addAttribute("postDto", post);
        return "post/form";
    }

    @GetMapping(value = "/genre/{genreId}")
    public String getPostByGenre(Model model, @PathVariable("genreId") Integer genreId, Principal principal) {
        if (principal != null) { //로그인 정보가 있을 때
            Member member = memberRepository.findByEmail(principal.getName());
            model.addAttribute("member", member.getNickName());
        }
        List<Post> posts = postService.getPostByGenreId(genreId);
        model.addAttribute("posts", posts);
        return "main";
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
        return "redirect:/post/{postId}";
    }

    @PostMapping (value = "/{postId}/delete")
    public @ResponseBody ResponseEntity deletePost(@PathVariable("postId") Long postId, HttpServletResponse response) {
        postService.deletePost(postId);
        return new ResponseEntity<Long>(postId,HttpStatus.OK);
    }
}

