package com.example.crudapp.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeCreateRequest {
	
	Long id;
	Long userId;
	Long postId;
	
}
