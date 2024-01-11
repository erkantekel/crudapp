package com.example.crudapp.dto.responses;

import com.example.crudapp.entities.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {
	
	Long id;
	Long userId;
	String userName;
	String text;

	public CommentResponse(Comment entity) {
		this.id = entity.getId();
		this.userId = entity.getUser().getId();
		this.userName = entity.getUser().getUserName();
		this.text = entity.getText();
	}
}
