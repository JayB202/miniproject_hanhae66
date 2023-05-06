package com.sparta.hanghae66.service;


import com.sparta.hanghae66.dto.*;
import com.sparta.hanghae66.entity.Comment;
import com.sparta.hanghae66.entity.Post;
import com.sparta.hanghae66.entity.User;
import com.sparta.hanghae66.entity.UserRole;
import com.sparta.hanghae66.repository.CommentRepository;
import com.sparta.hanghae66.repository.PostLikesRepository;
import com.sparta.hanghae66.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostLikesRepository postLikesRepository;

    @Transactional(readOnly = true)
    public List<PostDto> viewPostList(){
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostDto> postListDtoList = new ArrayList<>();
        for(Post post: postList) {
            PostDto postDto = new PostDto(post);
            postListDtoList.add(postDto);
        }
        return postListDtoList; // 리스트로
    }

    @Transactional(readOnly = true)
    public Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("게시글이 존재하지 않아요!"));
    }

    @Transactional(readOnly = true)
    public PostDto viewPost(Long postId) {
        Post post = findPost(postId);

        post.viewCountUp(postId);
        postRepository.save(post);

        //포스트리스폰스 + 코멘트리스폰스 + 라이크리스폰스
        List<CommentDto> commentDtoList = getAllComment();  // 요기를 commentRepository 에서 postId로 긁어오면 . . .?
        PostDto postDto = new PostDto(post);
        List<CommentDto> commentMatchDto = commentDtoList.stream()
                .filter(t -> Objects.equals(t.getCommentId(), postId))
                .collect(Collectors.toList());

        postDto.setCommentList(commentMatchDto);

        return postDto;  // 리스트로해서 리스폰스  + 코네트리스폰스
    }

    @Transactional(readOnly = true)
    public List<CommentDto> getAllComment() {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentDto> commentListDtoList = new ArrayList<>();

        for(Comment comment: commentList) {
            CommentDto commentDto = new CommentDto(comment);
            commentListDtoList.add(commentDto);
        }

        return commentListDtoList;
    }


    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, User user) {
        Post post = new Post(postRequestDto, user.getUsername());
        postRepository.save(post);
        return new PostResponseDto();

    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto, User user) {
        try {
            Post post = findPost(id);
            UserRole userRole = user.getRole();

            switch (userRole) {
                case USER:
                    if(StringUtils.pathEquals(post.getUsername(), user.getUsername())) {
                        post.update(postRequestDto);
                        PostResponseDto postResponseDto = new PostResponseDto(post.getId(), post.getTitle(), post.getUsername(), post.getPostLikesCount());
                        postResponseDto.setModifiedTime(post.getModifiedAt());
                        return postResponseDto;
                    }
                case ADMIN:
                    post.update(postRequestDto);
                    PostResponseDto postResponseDto = new PostResponseDto(post.getId(), post.getTitle(), post.getUsername(), post.getPostLikesCount());
                    postResponseDto.setModifiedTime(post.getModifiedAt());
                    return postResponseDto;
                default:
                    return null;
            }
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Transactional
    public ResponseDto deletePost(Long postId, User user) {
        try{
            Post post = findPost(postId);
            UserRole userRole = user.getRole();

            switch (userRole){
                case USER :
                    if(StringUtils.pathEquals(post.getUsername(), user.getUsername())){
                        postRepository.deleteById(postId);

                        return new ResponseDto("삭제완료", HttpStatus.OK);
                    }
                case ADMIN:
                    postRepository.deleteById(postId);
                    return new ResponseDto("삭제완료", HttpStatus.OK);
                default:
                    return null;
            }
        }
        catch (Exception ex) {
            throw ex;
        }
    }
}
