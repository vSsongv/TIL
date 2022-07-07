package com.SongForYou.controller;

import com.SongForYou.dto.PostListReadDto;
import com.SongForYou.entity.Member;
import com.SongForYou.entity.Post;
import com.SongForYou.repository.MemberRepository;
import com.SongForYou.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final PostService postService;

    @Autowired
    private final MemberRepository memberRepository;
    @GetMapping(value = "/")
    public String main(Model model, Principal principal) {
        if(principal != null){
            Member member = memberRepository.findByEmail(principal.getName());
            model.addAttribute("member",member.getNickName());
        }
        List<Post> posts = postService.postList();
        model.addAttribute("posts", posts);
        return "main";
    }
}
