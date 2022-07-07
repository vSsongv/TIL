package com.SongForYou.controller;

import com.SongForYou.dto.CommentDto;
import com.SongForYou.entity.Comment;
import com.SongForYou.entity.Post;
import com.SongForYou.service.CommentService;
import com.SongForYou.service.MemberService;
import com.SongForYou.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.http.HttpRequest;
import java.security.Principal;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;
    private final MemberService memberService;

    @PostMapping(value = "/new/{postId}")
    public String newComment(@Valid CommentDto commentDto, BindingResult bindingResult,
                             Model model, Principal principal, @PathVariable("postId") Long postId) {
        if(bindingResult.hasErrors()){
            return "/post/detail";
        }
        Long memberId = memberService.findByEmail(principal.getName());
        Post post = postService.findPost(postId);
        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .post(post)
                .build();
        try {
            Long commentId = commentService.createComment(comment, memberId);
        } catch(IllegalStateException e){
            model.addAttribute("errormessasge", "댓글작성중 에러가 발생했습니다");
            return "/post/detail";
        }
        return "redirect:/post/{postId}";
    }


    @PostMapping(value ="delete/{commentId}")
    public ResponseEntity<Long> deleteComment(@PathVariable("commentId")Long commentId, Model model, HttpServletResponse response) {
        try {
            commentService.deleteComment(commentId);
        }catch (Exception e){
            model.addAttribute("errorMessage", "댓글 삭제 중 에러가 발생하였습니다.");
        }
        return new ResponseEntity<Long>(commentId, HttpStatus.OK);
    }
}
