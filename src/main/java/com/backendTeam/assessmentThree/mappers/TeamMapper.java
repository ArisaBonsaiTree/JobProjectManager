package com.backendTeam.assessmentThree.mappers;

import com.backendTeam.assessmentThree.dtos.TeamRequestDto;
import com.backendTeam.assessmentThree.dtos.TeamResponseDto;
import com.backendTeam.assessmentThree.entities.Team;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TeamMapper {

    TeamResponseDto entityToDto(Team entity);
    
    List<TeamResponseDto> entitiesToDtos(List<Team> team);

	Team requestDtoToEntity(TeamRequestDto teamRequestDto);

}
