package com.sparta.hanghae66.service;

import com.sparta.hanghae66.dto.CommentDto;
import com.sparta.hanghae66.dto.MyPageResponseDto;
import com.sparta.hanghae66.dto.PostDto;
import com.sparta.hanghae66.entity.Comment;
import com.sparta.hanghae66.entity.Post;
import com.sparta.hanghae66.entity.User;
import com.sparta.hanghae66.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


    @Transactional
    public List<PostDto> getAllPost(String postUserId){
        List<Post> postList = postRepository.findAllPostByPostUserId(postUserId);
        List<PostDto> postDtoList = new ArrayList<>();

        for(Post post : postList){
            PostDto postDto = new PostDto(post);
            postDtoList.add(postDto);
        }
        return postDtoList;
    }

    @Transactional
    public List<CommentDto> getAllComment(String userId){
        List<Comment> commentList = commentRepository.findByCommentUserId(userId);
        List<CommentDto> commentDtoList = new ArrayList<>();

        for(Comment comment: commentList){
            CommentDto commentDto = new CommentDto(comment, comment.getPost().getPostId());
            commentDtoList.add(commentDto);

        }
        return commentDtoList;
    }

    @Transactional
    public MyPageResponseDto mypage(String userId){

        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("유저 정보 없음")
        );

        MyPageResponseDto myPageResponseDto = new MyPageResponseDto(user);
        List<PostDto> postDtoList = getAllPost(user.getId());
        List<CommentDto> commentDtoList = getAllComment(user.getId());
        myPageResponseDto.setPostList(postDtoList);
        myPageResponseDto.setCommentList(commentDtoList);
        return myPageResponseDto;
    }
}
