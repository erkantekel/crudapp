package com.example.crudapp.controllers;

import java.util.List;
import java.util.Optional;

import com.example.crudapp.entities.Comment;
import com.example.crudapp.dto.requests.CommentCreateRequest;
import com.example.crudapp.dto.requests.CommentUpdateRequest;
import com.example.crudapp.dto.responses.CommentResponse;
import com.example.crudapp.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

	private final CommentService commentService;

	@GetMapping
	public ResponseEntity<List<CommentResponse>> getAllComments(@RequestParam Optional<Long> userId,
												@RequestParam Optional<Long> postId) {
		return ResponseEntity.ok(commentService.getAllCommentsWithParam(userId, postId));
	}
	
	@PostMapping
	public ResponseEntity<Comment> createOneComment(@RequestBody CommentCreateRequest request) {
		return ResponseEntity.ok(commentService.createOneComment(request));
	}
	
	@GetMapping("/{commentId}")
	public ResponseEntity<Comment> getOneComment(@PathVariable Long commentId) {
		return ResponseEntity.ok(commentService.getOneCommentById(commentId));
	}
	
	@PutMapping("/{commentId}")
	public ResponseEntity<Comment> updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest request) {
		return ResponseEntity.ok(commentService.updateOneCommentById(commentId, request));
	}
	
	@DeleteMapping("/{commentId}")
	public void deleteOneComment(@PathVariable Long commentId) {
		commentService.deleteOneCommentById(commentId);
	}
}
