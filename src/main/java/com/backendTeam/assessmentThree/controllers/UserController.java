package com.backendTeam.assessmentThree.controllers;

import com.backendTeam.assessmentThree.dtos.CredentialsDto;
import com.backendTeam.assessmentThree.dtos.UserLoginResponseDto;
import com.backendTeam.assessmentThree.dtos.UserRequestDto;
import com.backendTeam.assessmentThree.dtos.UserResponseDto;

import com.backendTeam.assessmentThree.entities.User;
import com.backendTeam.assessmentThree.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {
	private final UserService userService;

	@GetMapping
	public List<UserResponseDto> getAllUsers(){
		return userService.getAllUsers();
	}

	@PostMapping("/login")
	public UserLoginResponseDto getUserByCredentials(@RequestBody CredentialsDto credentials){
		return userService.getUserByCredentials(credentials);
	}

	@PostMapping("/register")
	public UserResponseDto registerUser(@RequestBody UserRequestDto userRequestDto){
		return userService.registerUser(userRequestDto);
	}




}
