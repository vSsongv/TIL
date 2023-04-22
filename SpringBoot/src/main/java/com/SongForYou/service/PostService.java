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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    private final CommentRepository commentRepository;

//    @Transactional
//    public Long createPost(PostDto dto, Long memberId) {
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new IllegalArgumentException("not found member"));
//        Post post = Post.builder()
//                .title(dto.getTitle())
//                .content(dto.getContent())
//                .genreId(dto.getGenreId())
//                .link(dto.getLink())
//                .member(member)
//                .build();
//        Long postId = postRepository.save(post).getId();
//        return postId;
//    }
    @Transactional
    public Long updatedPost(Long id, Post post){
        Post getPost = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("isn't exist post"));
        getPost.setTitle(post.getTitle());
        getPost.setContent(post.getContent());
        getPost.setGenreId(post.getGenreId());
        return getPost.getId();
    }
    @Transactional
    public void deletePost(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("isn't exist post"));
        postRepository.delete(post);
    }
    public Post findPost(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("isn't exist post"));
    }
    public List<Post> postList(){
        List<Post> postList = postRepository.findAll();
        return postList;
    }

//    public PostReadDto readPost(Long id){
//        Post post = findPost(id);
//        Integer numOfComment = countedCommentByPostId(id);
//        List<CommentReadDto> commentReadDtoList = commentRepository.readComment(post.getComments());
//        PostReadDto readPost = new PostReadDto(post, numOfComment, commentReadDtoList);
//        return readPost;
//    }
    public Integer countedCommentByPostId(Long postId){
        Integer countedCommenet = commentRepository.countByPostId(postId);
        return countedCommenet;
    }
}
