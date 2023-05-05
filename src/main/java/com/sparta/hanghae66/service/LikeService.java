package com.sparta.hanghae66.service;

import com.sparta.hanghae66.dto.ResponseDto;
import com.sparta.hanghae66.entity.User;
import com.sparta.hanghae66.repository.CommentLikesRepository;
import com.sparta.hanghae66.repository.PostLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostLikesRepository postLikesRepository;
    private final CommentLikesRepository commentLikesRepository;

    @Transactional
    public ResponseDto postLikeService(Long postId, User user) {

//        Optional<PostLikes> likesCheck = postLikesRepository.findByUserIdAndPostId(user.getId(), postId);
//
//        if(likesCheck.isPresent()) {
//            postLikesRepository.update(likesCheck.get());
//        } else {
//            PostLikes postLikes = new PostLikes(postId, user.getUsername(), true);
//            postLikesRepository.save(postLikes);
//        }


        return new ResponseDto("좋아요!", HttpStatus.OK);
    }

    public ResponseDto commentLikeService(Long commentId, User user) {
//
//        if(likesCheck.isPresent()) {
//            postLikesRepository.update(likesCheck.get());
//        } else {
//            CommentLikes commentLikes = new CommentLikes(commentId, user.getUsername(), true, postId);
//            postLikesRepository.save(postLikes);
//        }

        return new ResponseDto("좋아요!", HttpStatus.OK);
    }
}
