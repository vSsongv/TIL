package com.SongForYou.service;

import com.SongForYou.dto.CommentReadDto;
import com.SongForYou.dto.PostDto;
import com.SongForYou.dto.PostReadDto;
import com.SongForYou.entity.Member;
import com.SongForYou.entity.Post;
import com.SongForYou.repository.CommentRepository;
import com.SongForYou.repository.MemberRepository;
import com.SongForYou.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Service
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceTest {
    private final PostRepository postRepository;
    private final PostService postService;
    private final MemberRepository memberRepository;

    public Member saveMember() {
        Member member = new Member();
        member.setEmail("test");
        return memberRepository.save(member);
    }

    @Test
    @DisplayName("게시글등록테스트")
    public void savePost() {
        Member member = saveMember();

        PostDto postDto = new PostDto();
        postDto.setTitle("제목");
        postDto.setContent("내용");
        postDto.setLink("testst");
        postDto.setGenreId(1);

        Long postId = postService.createPost(postDto, member.getId());

        Optional<Post> postReadDto = postRepository.findById(postId);

        assertEquals(postReadDto.get().getContent(), postDto.getContent());
    }
}
