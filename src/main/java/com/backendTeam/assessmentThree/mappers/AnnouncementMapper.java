package com.backendTeam.assessmentThree.mappers;
import java.util.List;

import org.mapstruct.Mapper;

import com.backendTeam.assessmentThree.dtos.AnnouncementRequestDto;
import com.backendTeam.assessmentThree.dtos.AnnouncementResponseDto;
import com.backendTeam.assessmentThree.entities.Announcements;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CompanyMapper.class})
public interface AnnouncementMapper {

    List<AnnouncementResponseDto> entitiesToDtos(List<Announcements> announcements);
    Announcements requestDtoToEntity(AnnouncementRequestDto announcementRequestDto);
    AnnouncementResponseDto entityToDto(Announcements entity);
}
