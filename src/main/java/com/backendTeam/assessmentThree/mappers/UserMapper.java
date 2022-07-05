package com.backendTeam.assessmentThree.mappers;

import com.backendTeam.assessmentThree.dtos.TeamResponseDto;
import com.backendTeam.assessmentThree.dtos.UserLoginResponseDto;
import com.backendTeam.assessmentThree.dtos.UserRequestDto;
import com.backendTeam.assessmentThree.dtos.UserResponseDto;
import com.backendTeam.assessmentThree.entities.Team;
import com.backendTeam.assessmentThree.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProfileMapper.class, CredentialsMapper.class})
public interface UserMapper {



    List<UserResponseDto> entitiesToDtos(List<User> user);
    User entity(User entity);

    User requestDtoToEntity(UserRequestDto userRequestDto);

    UserResponseDto entityToDtos(User entity);
    UserLoginResponseDto entityToDto(User entity);



}
