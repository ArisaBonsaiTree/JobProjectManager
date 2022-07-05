package com.backendTeam.assessmentThree.mappers;

import com.backendTeam.assessmentThree.dtos.ProfileDto;
import com.backendTeam.assessmentThree.embeddables.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileDto embeddableToDto(Profile profile);

    Profile dtoToEmbeddable(ProfileDto profileDto);
}
