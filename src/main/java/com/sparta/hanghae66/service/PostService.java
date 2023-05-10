package com.sparta.hanghae66.service;


import com.sparta.hanghae66.dto.*;
import com.sparta.hanghae66.entity.*;
import com.sparta.hanghae66.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostLikesRepository postLikesRepository;
    private final CommentLikesRepository commentLikesRepository;
    private final VisitInfoRepository visitInfoRepository;

    //게시글 전체 조회(코멘트 안보임)
    @Transactional(readOnly = true)
    public List<PostDto> viewPostList() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostDto> postListDtoList = new ArrayList<>();
        Long cmtSize = 0L;
        for (Post post : postList) {
            PostDto postDto = new PostDto(post);
            cmtSize = Long.valueOf(post.getCommentList().size());
            postDto.setCmtCount(cmtSize);
            postListDtoList.add(postDto);
        }
        return postListDtoList; // 리스트로
    }


    @Transactional(readOnly = true)
    public Post findPost(Long postId) {
        return postRepository.findByPostId(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않아요!"));
    }

    //게시글 선택 조회 코멘트 보임
    @Transactional
    public PostDto viewPost(Long postId, User user, jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response ) {
        String userId = user.getId();
        Post post = findPost(postId);
        PostDto postDto = new PostDto(post);
        List<CommentDto> commentDtoList = getAllComment(postId, userId);  // 요기를 commentRepository 에서 postId로 긁어오면 . . .?
        postViewCheck(userId, post, postDto);
        Long cmtCnt = Long.valueOf(commentDtoList.size());
        post.setCmtCount(cmtCnt);
        postDto.setCmtCount(cmtCnt);
        postRepository.save(post);
        Boolean chkPostLikes = chkLikePost(postId, userId);
        postDto.setChkpostLikes(chkPostLikes);
        postDto.setCommentList(commentDtoList);

        return postDto;  // 리스트로해서 리스폰스  + 코네트리스폰스
        //조회수, 댓글수 여기서 매핑해서 저장함(개별조회에서 최신화됨)
//        Cookie oldCookie = null;
//        Cookie[] cookies = request.getCookies();
//        Long visitCnt = 0L;
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("postView")) {
//                    oldCookie = cookie;
//                }
//            }
//        }
//
//        if (oldCookie != null) {
//            if (!oldCookie.getValue().contains("[" + postId.toString() + "]")) {
//                visitCnt = post.getPostVisitCnt();
//                visitCnt++;
//                post.setPostVisitCnt(visitCnt);
//                oldCookie.setValue(oldCookie.getValue() + "_[" + postId + "]");
//                oldCookie.setPath("/");
//                oldCookie.setDomain(".hanghae66.s3-website.ap-northeast-2.amazonaws.com");
//                oldCookie.setMaxAge(60 * 60 * 12);
//
//                oldCookie.setSecure(true);
//                oldCookie.setHttpOnly(false);
//
//                response.addCookie(oldCookie);
//            }
//        } else {
//            visitCnt = post.getPostVisitCnt();
//            visitCnt++;
//            post.setPostVisitCnt(visitCnt);
//            Cookie newCookie = new Cookie("postView","[" + postId + "]");
//            newCookie.setPath("/");
//            newCookie.setDomain(".hanghae66.s3-website.ap-northeast-2.amazonaws.com");
//            newCookie.setMaxAge(60 * 60 * 12);
//
//            newCookie.setSecure(true);
//            newCookie.setHttpOnly(false);
//
//            response.addCookie(newCookie);
//        }
    }

    @Transactional(readOnly = true)
    public List<CommentDto> getAllComment(Long postId, String userId) {
        List<Comment> commentList = commentRepository.findAllCommentById(postId);
        List<CommentDto> commentListDtoList = new ArrayList<>();
        boolean chkLike;

        for (Comment comment : commentList) {
            com.sparta.hanghae66.dto.CommentDto commentDto = new com.sparta.hanghae66.dto.CommentDto(comment, comment.getPost().getPostId());
            chkLike = chkLikeComment(commentDto.getCmtId(), userId);
            commentDto.setChkCommentLikes(chkLike);
            commentListDtoList.add(commentDto);
        }
        return commentListDtoList;
    }

    @Transactional(readOnly = true)
    public boolean chkLikePost(Long postId, String userId) {
        Optional<PostLikes> isLike = postLikesRepository.findByPostLikesUserIdAndPostLikesId(userId, postId);
        if (isLike.isPresent()) {
            PostLikes postLikes = postLikesRepository.findByUserId(postId, userId);
            return postLikes.isPostLikes();
        } else
            return false;
    }

    @Transactional(readOnly = true)
    public boolean chkLikeComment(Long commentId, String userId) {
        Optional<CommentLikes> isLike = commentLikesRepository.findByUserId_Opt(commentId, userId);
        if(isLike.isPresent()) {
            CommentLikes commentLikes = commentLikesRepository.findByUserId(commentId, userId);
            return commentLikes.isCmtLikes();
        } else
            return false;
    }


    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, User user) {
        Post post = new Post(postRequestDto, user.getUserName(), user.getId(), user.getUserSkill(), user.getUserYear());
        postRepository.save(post);
        PostResponseDto postResponseDto = new PostResponseDto(post);
        postResponseDto.setUserSkill(user.getUserSkill());
        postResponseDto.setUserYear(String.valueOf(user.getUserYear()));
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
                    if (StringUtils.pathEquals(post.getPostUserId(), user.getId())) {
                        post.update(postRequestDto);
                        PostResponseDto postResponseDto = new PostResponseDto(post.getPostId(), post.getPostTitle(), post.getPostContent(), post.getPostSkill(), post.getPostFile(), post.getPostLikes(), post.getPostUserId(), post.getPostUserName(), post.getPostVisitCnt(), post.getCmtCount());
                        postResponseDto.setModifiedTime(post.getModifiedAt());
                        return postResponseDto;
                    }
                    break;
                case ADMIN:
                    post.update(postRequestDto);
                    PostResponseDto postResponseDto = new PostResponseDto(post.getPostId(), post.getPostTitle(), post.getPostContent(), post.getPostSkill(), post.getPostFile(), post.getPostLikes(), post.getPostUserId(), post.getPostUserName(), post.getPostVisitCnt(), post.getCmtCount());
                    postResponseDto.setModifiedTime(post.getModifiedAt());
                    return postResponseDto;

                default:
                    return null;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return null;
    }

    @Transactional
    public ResponseDto deletePost(Long postId, User user) {
        try {
            Post post = findPost(postId);
            UserRole userRole = user.getRole();

            switch (userRole) {
                case USER:
                    if (StringUtils.pathEquals(post.getPostUserId(), user.getId())) {
                        postRepository.deleteById(postId);
                        return new ResponseDto("삭제완료", HttpStatus.OK);
                    }
                    break;

                case ADMIN:
                    postRepository.deleteById(postId);
                    return new ResponseDto("관리자에 의해 삭제된 게시글입니다.", HttpStatus.OK);

                default:
                    return null;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return new ResponseDto("삭제할 권한이 없습니다.", HttpStatus.BAD_REQUEST);
    }

    @Transactional(readOnly = true)
    public List<PostDto> searchPost(String keyword, String sortBy){
        List<Post> postList = new ArrayList<>();
        List<PostDto> postListDtoList = new ArrayList<>();
        Long cmtSize = 0L;

        if(sortBy.equals("title")) {
            postList = postRepository.findAllByPostTitleContaining(keyword);
        }

        if(sortBy.equals("content")) {
            postList = postRepository.findAllByPostContentContaining(keyword);
        }

        if(sortBy.equals("all")){
            postList = postRepository.findAllByPostTitleContainingOrPostContentContaining(keyword);
        }

        if(postList.size() == 0) {
            return postListDtoList;
        }

        for (Post post : postList) {
            PostDto postDto = new PostDto(post);
            cmtSize = Long.valueOf(post.getCommentList().size());
            postDto.setCmtCount(cmtSize);
            postListDtoList.add(postDto);
        }
        return postListDtoList; // 리스트로
    }

    @Transactional
    public void postViewCheck(String userId, Post post, PostDto postDto) {
        Long postId =post.getPostId();
        String visitDat = "";
        List<String> visitDataList = new ArrayList<>();
        int crDat = 0;
        int crTime = 0;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        VisitInfo visitInfo = null;
        crDat = Integer.parseInt(nowDate.toString().replaceAll("-", ""));
        crTime = Integer.parseInt(nowTime.format(timeFormatter));

        Optional<VisitInfo> chkVisitInfo = visitInfoRepository.findVisitInfoByVisitUserId_Opt(userId);
        if(chkVisitInfo.isPresent())
        {
            visitInfo = visitInfoRepository.findVisitInfoByVisitUserId_ent(userId);
            //현재날짜가 visitInfo의 데이터 날짜보다 크거나 같을때 1, // 20230509
            if(crDat > visitInfo.getVisitCrdAt()) {
                visitDat = postId + ", ";
                visitInfo.setVisitCrdAt(crDat);
                visitInfo.setVisitCrTime(crTime);
                visitInfo.setVisitData(visitDat);
                visitInfoRepository.save(visitInfo);
                visitCnt(post, postDto);
            }
            else {   //crDat이 작을때 -> visitInfo의 데이터 날짜가 더 클때
                visitDat = visitInfo.getVisitData(); // 1, 2,
                visitDataList = Arrays.asList(visitDat.split(", "));
                if(!visitDataList.contains(postId.toString())) {
                    visitDat += postId + ", ";  //1, 2, 3,
                    visitCnt(post, postDto);
                }
                visitInfo.setVisitData(visitDat);
                visitInfoRepository.save(visitInfo);
            }
        }
        else {
            visitDat = postId + ", ";     //1, 2, 3, 4, .....
            visitInfo = new VisitInfo(userId, visitDat, crDat, crTime);
            visitInfoRepository.save(visitInfo);
            visitCnt(post, postDto);
        }
    }

    public void visitCnt(Post post, PostDto postDto) {
        Long visitCnt;
        visitCnt = post.getPostVisitCnt();
        visitCnt++;
        postDto.setPostVisitCnt(visitCnt);
        post.setPostVisitCnt(visitCnt);
    }
}
