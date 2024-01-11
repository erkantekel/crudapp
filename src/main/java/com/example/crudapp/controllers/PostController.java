package com.example.crudapp.controllers;

import java.util.List;

import com.example.crudapp.entities.Post;
import com.example.crudapp.dto.requests.PostCreateRequest;
import com.example.crudapp.dto.requests.PostUpdateRequest;
import com.example.crudapp.dto.responses.PostResponse;
import com.example.crudapp.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

	private final PostService postService;

	@GetMapping
	public ResponseEntity<List<PostResponse>> getAllPosts(@RequestParam(required = false) Long userId) {
		return ResponseEntity.ok(postService.getAllPosts(userId));
	}

	@PostMapping
	public ResponseEntity<Post> createPost(@RequestBody PostCreateRequest newPostRequest) {
		return ResponseEntity.ok(postService.createOnePost(newPostRequest));
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
		return ResponseEntity.ok(postService.getOnePostByIdWithLikes(postId));
	}

	
	@PutMapping("/{postId}")
	public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost) {
		return ResponseEntity.ok(postService.updateOnePostById(postId, updatePost));
	}
	
	@DeleteMapping("/{postId}")
	public void deletePost(@PathVariable Long postId) {
		postService.deleteOnePostById(postId);
	}
}
