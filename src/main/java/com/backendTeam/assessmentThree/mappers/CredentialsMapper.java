package com.backendTeam.assessmentThree.mappers;

import com.backendTeam.assessmentThree.dtos.CredentialsDto;
import com.backendTeam.assessmentThree.embeddables.Credentials;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {

    Credentials dtoToEmbeddable(CredentialsDto credentialsDto);
}