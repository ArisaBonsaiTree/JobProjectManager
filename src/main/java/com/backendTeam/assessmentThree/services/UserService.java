package com.backendTeam.assessmentThree.services;

import com.backendTeam.assessmentThree.dtos.CredentialsDto;
import com.backendTeam.assessmentThree.dtos.UserLoginResponseDto;
import com.backendTeam.assessmentThree.dtos.UserRequestDto;
import com.backendTeam.assessmentThree.dtos.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();

    UserLoginResponseDto getUserByCredentials(CredentialsDto credentials);

    UserResponseDto registerUser(UserRequestDto userRequestDto);
}