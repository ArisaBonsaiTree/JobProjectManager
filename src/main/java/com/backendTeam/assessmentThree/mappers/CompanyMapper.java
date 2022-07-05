package com.backendTeam.assessmentThree.mappers;

import com.backendTeam.assessmentThree.dtos.CompanyRequestDto;
import com.backendTeam.assessmentThree.dtos.CompanyResponseDto;
import com.backendTeam.assessmentThree.entities.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TeamMapper.class})
public interface CompanyMapper {


 
    List<CompanyResponseDto> entitiesToResponseDtos(List<Company> entities);

    Company requestDtoToEntity(CompanyRequestDto companyRequestDto);

    CompanyResponseDto entityToDto(Company entity);


}
