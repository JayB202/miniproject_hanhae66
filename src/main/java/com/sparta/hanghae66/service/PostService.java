package com.sparta.hanghae66.service;


import com.sparta.hanghae66.dto.*;
import com.sparta.hanghae66.entity.*;
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
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostLikesRepository postLikesRepository;

    //게시글 전체 조회(코멘트 안보임)
    @Transactional(readOnly = true)
    public List<PostDto> viewPostList(){
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostDto> postListDtoList = new ArrayList<>();
        Long cmtSize = 0L;
        for(Post post: postList) {
            PostDto postDto = new PostDto(post);
            cmtSize = Long.valueOf(post.getCommentList().size());
            postDto.setCmtCount(cmtSize);
            postListDtoList.add(postDto);
        }
        return postListDtoList; // 리스트로
    }

    @Transactional(readOnly = true)
    public Post findPost(Long postId) {
        return postRepository.findByPostId(postId).orElseThrow( () -> new IllegalArgumentException("게시글이 존재하지 않아요!"));
    }

    //게시글 선택 조회 코멘트 보임
    @Transactional
    public PostDto viewPost(Long postId, User user) {
        Post post = findPost(postId);
        //포스트리스폰스 + 코멘트리스폰스 + 라이크리스폰스
        PostDto postDto = new PostDto(post);
        List<CommentDto> commentDtoList = getAllComment(postId);  // 요기를 commentRepository 에서 postId로 긁어오면 . . .?

        //조회수, 댓글수 여기서 매핑해서 저장함(개별조회에서 최신화됨)
        Long visitCnt = post.getPostVisitCnt();
        visitCnt++;
        post.setPostVisitCnt(visitCnt);

        Long cmtCnt = Long.valueOf(commentDtoList.size());
        post.setCmtCount(cmtCnt);
        postDto.setCmtCount(cmtCnt);
        postRepository.save(post);

//        List<CommentDto> commentMatchDto = commentDtoList.stream()
//                .filter(t -> Objects.equals(t.getCmtUserId(), postId))
//                .collect(Collectors.toList());

        Boolean chkPostLikes = chkLikePost(postId, user.getId());
        postDto.setChkpostLikes(chkPostLikes);

        postDto.setCommentList(commentDtoList);

        return postDto;  // 리스트로해서 리스폰스  + 코네트리스폰스
    }

    @Transactional(readOnly = true)
    public List<CommentDto> getAllComment(Long postId) {
        List<Comment> commentList = commentRepository.findAllCommentById(postId);
        List<CommentDto> commentListDtoList = new ArrayList<>();

        for(Comment comment: commentList) {
            CommentDto commentDto = new CommentDto(comment);
            commentListDtoList.add(commentDto);
        }

        return commentListDtoList;
    }

    @Transactional(readOnly = true)
    public boolean chkLikePost(Long postId, String userId) {
        Optional<PostLikes> isLike = postLikesRepository.findByPostLikesUserIdAndPostLikesId(userId, postId);
        if(isLike.isPresent())
        {
            PostLikes postLikes = postLikesRepository.findByUserId(postId, userId);
            return postLikes.isPostLikes();
        }
        else
            return false;
    }


    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, User user) {
        Post post = new Post(postRequestDto, user.getUserName(), user.getId(), user.getUserSkill());
        postRepository.save(post);
        PostResponseDto postResponseDto = new PostResponseDto(post);
        postResponseDto.setUserSkill(user.getUserSkill());
        postResponseDto.setCreatedAt(post.getCreatedAt());
        postResponseDto.setPostSkill(postRequestDto.getPostSkill());
        return postResponseDto;
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto, User user) {
        try {
            Post post = findPost(id);
            UserRole userRole = user.getRole();

            switch (userRole) {
                case USER:
                    if(StringUtils.pathEquals(post.getPostUserId(), user.getId())) {
                        post.update(postRequestDto);
                        PostResponseDto postResponseDto = new PostResponseDto(post.getPostId(), post.getPostTitle(), post.getPostContent(), post.getPostSkill(), post.getPostFile(), post.getPostLikes(), post.getPostUserId(), post.getPostUserName(), post.getPostVisitCnt(), post.getCmtCount());
                        postResponseDto.setModifiedTime(post.getModifiedAt());
                        return postResponseDto;
                    }
                case ADMIN:
                    post.update(postRequestDto);
                    PostResponseDto postResponseDto = new PostResponseDto(post.getPostId(), post.getPostTitle(), post.getPostContent(), post.getPostSkill(), post.getPostFile(), post.getPostLikes(), post.getPostUserId(), post.getPostUserName(), post.getPostVisitCnt(), post.getCmtCount());
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
                    if(StringUtils.pathEquals(post.getPostUserId(), user.getId())){
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
