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
    private final CommentService commentService;
    @Transactional
    public Long createPost(PostDto dto, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("not found member"));
        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .genreId(dto.getGenreId())
                .link(dto.getLink())
                .member(member)
                .build();
        Long postId = postRepository.save(post).getId();
        return postId;
    }
    @Transactional
    public Long updatedPost(PostDto postdto){
        Post getPost = postRepository.findById(postdto.getId())
                .orElseThrow(() -> new IllegalArgumentException("isn't exist post"));
        getPost.setTitle(postdto.getTitle());
        getPost.setContent(postdto.getContent());
        getPost.setGenreId(postdto.getGenreId());
        getPost.setLink(postdto.getLink());
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

    public PostReadDto readPost(Long id){
        Post post = findPost(id);
        Integer numOfComment = countedCommentByPostId(id);
        List<CommentReadDto> commentReadDtoList = commentService.readComment(post.getComments());
        PostReadDto readPost = new PostReadDto(post, numOfComment, commentReadDtoList);
        return readPost;
    }
    public Integer countedCommentByPostId(Long postId){
        Integer countedComment = commentRepository.countByPostId(postId);
        return countedComment;
    }

    public List<Post> getPostByGenreId(int genreId) {
        List<Post> genrePost = postRepository.findAllByGenreId(genreId);
        return genrePost;
    }
}
