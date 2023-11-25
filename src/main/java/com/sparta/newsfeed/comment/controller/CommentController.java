package com.sparta.newsfeed.comment.controller;


import com.sparta.newsfeed.comment.dto.CommentRequestDto;
import com.sparta.newsfeed.comment.dto.CommentResponseDto;
import com.sparta.newsfeed.comment.service.CommentService;
import com.sparta.newsfeed.member.domain.Member;
import com.sparta.newsfeed.security.service.MemberDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @Operation(summary = "댓글 작성", description = "로그인한 사용자는 게시글에 댓글을 남길 수 있다.")
    @PostMapping("/boards/{boardId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long boardId, @RequestBody CommentRequestDto commentRequestDto,
                                                            @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return commentService.createComment(boardId, commentRequestDto, memberDetails.getMember());
    }

    /*// 댓글 조회
    @GetMapping("/comment/{boardId}")
    public List<CommentResponseDto> getComments(@PathVariable Long boardId) {
        return commentService.getComments(boardId);
    }*/

    // 댓글 수정
    @Operation(summary = "댓글 수정", description = "로그인한 사용자는 자신의 댓글을 수정할 수 있다.")
    @PutMapping("/boards/{boardId}/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto,
                                            @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return commentService.updateComment(commentId, commentRequestDto, memberDetails.getMember());
    }
    
    // 댓글 삭제
    @Operation(summary = "댓글 삭제", description = "로그인한 사용자는 자신의 댓글을 삭제할 수 있다.")
    @DeleteMapping("/boards/{boardId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return commentService.deleteComment(commentId, memberDetails.getMember());
    }
}
