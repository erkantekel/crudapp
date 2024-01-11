package com.example.crudapp.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequest {

	Long id;
	String text;
	String title;
	Long userId;
}
