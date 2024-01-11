package com.example.crudapp.controllers;

import java.util.List;
import java.util.Objects;

import com.example.crudapp.entities.User;
import com.example.crudapp.exceptions.UserNotFoundException;
import com.example.crudapp.dto.responses.UserResponse;
import com.example.crudapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;

	@GetMapping
	public List<UserResponse> getAllUsers(){
		return userService.getAllUsers().stream().map(UserResponse::new).toList();
	}
	
	@PostMapping
	public ResponseEntity<Void> createUser(@RequestBody User newUser) {
		User user = userService.saveOneUser(newUser);
		if(!Objects.isNull(user))
			return new ResponseEntity<>(HttpStatus.CREATED);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserResponse> getOneUser(@PathVariable Long userId) {
		User user = userService.getOneUserById(userId);
		if(Objects.isNull(user))
			throw new UserNotFoundException();

		return ResponseEntity.ok(new UserResponse(user));
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<Void> updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
		User user = userService.updateOneUser(userId, newUser);
		if(!Objects.isNull(user))  return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@DeleteMapping("/{userId}")
	public void deleteOneUser(@PathVariable Long userId) {
		userService.deleteById(userId);
	}
	
	@GetMapping("/activity/{userId}")
	public List<Object> getUserActivity(@PathVariable Long userId) {
		return userService.getUserActivity(userId);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private void handleUserNotFound() {
		
	}
}
