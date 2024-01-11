package com.example.crudapp.dto.responses;


import com.example.crudapp.entities.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponse {
	
	private Long id;
	private Long userId;
	private String userName;
	private String title;
	private String text;

	public static PostResponse fromPost(Post entity) {
		PostResponse postResponse = new PostResponse();
		postResponse.setId(entity.getId());
		postResponse.setText(entity.getText());
		postResponse.setTitle(entity.getTitle());
		postResponse.setUserName(entity.getUser().getUserName());
		postResponse.setUserId(entity.getUser().getId());
		return postResponse;
	}
}
