package com.songforyou.service;


import com.songforyou.dto.PostDto;
import com.songforyou.entity.Post;
import com.songforyou.repository.CommentRepository;
import com.songforyou.repository.PostRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
   private final PostRepository postRepository;
   private final CommentRepository commentRepository;

    public Long createPost(PostDto postDto){
        //Post post = Post.createPost(postDto);
        //System.out.println("dsfdf"+post.getContent());
        //System.out.println("dsfdf"+post.getContent());
        //System.out.println("dsfdf"+post.getContent());
        //postRepository.save(post);
        Long postId = postRepository.save(Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .genreId(postDto.getGenreId())
                .link(postDto.getLink())
                .build()).getId();

        return postId;
    }

    public Long updatedPost(Long id, Post post){
        Post getPost = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("isn't exist post"));
        getPost.setTitle(post.getTitle());
        getPost.setContent(post.getContent());
        getPost.setGenreId(post.getGenreId());
        return getPost.getId();
    }
    @Transactional
    public void deleteBoard(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("isn't exist post"));
        postRepository.delete(post);
    }
    public Post findPost(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("isn't exist post"));
    }
    public List<Post> postList(){return postRepository.findAll();}
    public List<Post> findAll(Long userId){return postRepository.findAll();}
//    public PostDto readPost(Long id){
//        Post post = findPost(id);
//        Integer numOfComment = countedCommentByPostId(id);
//        //List<CommentReadDto> commentReadDtoList = commentService.readComment(post.getComments());
//        //PostDto readPost = new PostDto(post, numOfComment, commentReadDtoList);
//        return readPost;
//    }
    public Integer countedCommentByPostId(Long postId){
        Integer countedCommenet = commentRepository.countByPostId(postId);
        return countedCommenet;
    }
}