package com.backendTeam.assessmentThree.mappers;

import com.backendTeam.assessmentThree.dtos.CompanyRequestDto;
import com.backendTeam.assessmentThree.dtos.CompanyResponseDto;
import com.backendTeam.assessmentThree.dtos.ProjectRequestDto;
import com.backendTeam.assessmentThree.dtos.ProjectResponseDto;
import com.backendTeam.assessmentThree.entities.Company;
import com.backendTeam.assessmentThree.entities.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TeamMapper.class})
public interface ProjectMapper {
    List<ProjectResponseDto> entitiesToDtos(List<Project> entities);

    ProjectResponseDto entityToDto(Project entity);

    Project requestDtoToEntity(ProjectRequestDto projectRequestDto);
}
