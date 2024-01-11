package com.example.crudapp.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequest {
	
	Long id;
	Long userId;
	Long postId;
	String text;

}
