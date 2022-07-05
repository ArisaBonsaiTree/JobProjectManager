package com.backendTeam.assessmentThree.services;

import java.util.List;

import com.backendTeam.assessmentThree.dtos.*;

public interface CompanyService {

    List<CompanyResponseDto> getAllCompanies();

    List<UserResponseDto> getUsersFromCompany(Long id);

    List<TeamResponseDto> getTeamsFromCompany(Long id);

    List<AnnouncementResponseDto> getAnnouncementsFromCompany(Long id);

    CompanyResponseDto createCompany(CompanyRequestDto companyRequestDto);

    public List<ProjectResponseDto> getProjectsByTeamByCompany(Long id, Long secondId);


    ProjectResponseDto editCompanyTeamProjectDescription(DescriptionDto descriptionDto, Long companyId, Long teamId, Long projectId);

    TeamResponseDto deleteTeamFromCompany(Long companyId, Long teamId);

    ProjectResponseDto deleteProjectFromTeamFromCompany(Long companyId, Long teamId, Long projectId);

	ProjectResponseDto createProjectForTeamAndCompany(ProjectRequestDto projectRequestDto, Long teamId, Long companyId);

	TeamResponseDto createTeamForCompany(TeamRequestDto teamRequestDto, Long companyId);

    AnnouncementResponseDto createAnnouncementForCompany(AnnouncementRequestDto announcementRequestDto, Long companyId);

    TeamResponseDto editTeam(DescriptionDto descriptionDto, Long companyId, Long teamId);

    List<UserResponseDto> getUsersFromTeamUnderCompany(Long companyId, Long teamId);
}