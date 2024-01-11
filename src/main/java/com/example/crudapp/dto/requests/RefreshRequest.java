package com.example.crudapp.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshRequest {

	Long userId;
	String refreshToken;
}
