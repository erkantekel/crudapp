package com.example.crudapp.services;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.crudapp.entities.Post;
import com.example.crudapp.entities.User;
import com.example.crudapp.repos.PostRepository;
import com.example.crudapp.dto.requests.PostCreateRequest;
import com.example.crudapp.dto.requests.PostUpdateRequest;
import com.example.crudapp.dto.responses.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final UserService userService;

	public List<PostResponse> getAllPosts(Long userId) {
		List<Post> list;
		if(!Objects.isNull(userId)) {
			 list = postRepository.findByUserId(userId);
		}else
			list = postRepository.findAll();

		List<PostResponse> postResponseList = list.stream().map(PostResponse::fromPost).collect(Collectors.toList());
		return postResponseList;
	}

	public Post getOnePostById(Long postId) {
		return postRepository.findById(postId).orElse(null);
	}

	public PostResponse getOnePostByIdWithLikes(Long postId) {
		Post post = postRepository.findById(postId).orElse(null);
		return PostResponse.fromPost(post);
	}
	
	public Post createOnePost(PostCreateRequest newPostRequest) {
		User user = userService.getOneUserById(newPostRequest.getUserId());
		if(user == null)
			return null;
		Post toSave = new Post();
		toSave.setId(newPostRequest.getId());
		toSave.setText(newPostRequest.getText());
		toSave.setTitle(newPostRequest.getTitle());
		toSave.setUser(user);
		toSave.setCreateDate(new Date());
		return postRepository.save(toSave);
	}

	public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
		Optional<Post> post = postRepository.findById(postId);
		if(post.isPresent()) {
			Post toUpdate = post.get();
			toUpdate.setText(updatePost.getText());
			toUpdate.setTitle(updatePost.getTitle());
			postRepository.save(toUpdate);
			return toUpdate;
		}
		return null;
	}

	public void deleteOnePostById(Long postId) {
		postRepository.deleteById(postId);
	}
	
}
