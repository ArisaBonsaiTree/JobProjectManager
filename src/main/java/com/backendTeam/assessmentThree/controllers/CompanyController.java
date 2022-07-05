
package com.backendTeam.assessmentThree.controllers;

import com.backendTeam.assessmentThree.dtos.*;
import com.backendTeam.assessmentThree.services.CompanyService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("companies")
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;


    @GetMapping
    public List<CompanyResponseDto> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}/users")
    public List<UserResponseDto> getUsersFromCompany(@PathVariable Long id) {
        return companyService.getUsersFromCompany(id);
    }

    @GetMapping("/{companyId}/teams/{teamId}/users")
    public List<UserResponseDto> getUsersFromTeamUnderCompany(@PathVariable Long companyId, @PathVariable Long teamId){
        return companyService.getUsersFromTeamUnderCompany(companyId, teamId);
    }

    @GetMapping("/{id}/teams")
    public List<TeamResponseDto> getTeamsFromCompany(@PathVariable Long id) {
        return companyService.getTeamsFromCompany(id);
    }

    @GetMapping("/{id}/announcements")
    public List<AnnouncementResponseDto> getAnnouncementsFromCompany(@PathVariable Long id) {
        return companyService.getAnnouncementsFromCompany(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponseDto createCompany(@RequestBody CompanyRequestDto companyRequestDto){
        return companyService.createCompany(companyRequestDto);
    }
    
    @PostMapping("/{companyId}/teams")
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponseDto createTeamForCompany(@RequestBody TeamRequestDto teamRequestDto, @PathVariable Long companyId){
        return companyService.createTeamForCompany(teamRequestDto, companyId);
    }
    
    @PostMapping("/{companyId}/teams/{teamId}/projects")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponseDto createProjectForTeamAndCompany(@RequestBody ProjectRequestDto projectRequestDto, @PathVariable Long teamId, @PathVariable Long companyId){
        return companyService.createProjectForTeamAndCompany(projectRequestDto, teamId, companyId);
    }
     
    @PostMapping("/{companyId}/announcements")
    @ResponseStatus(HttpStatus.CREATED)
    public AnnouncementResponseDto createAnnouncementForCompany(@RequestBody AnnouncementRequestDto announcementRequestDto, @PathVariable Long companyId){
        return companyService.createAnnouncementForCompany(announcementRequestDto, companyId);
    }

    @GetMapping("/{id}/teams/{secondId}/projects")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProjectResponseDto> getProjectsByTeamByCompany(@PathVariable Long id, @PathVariable Long secondId){
        return companyService.getProjectsByTeamByCompany(id, secondId);
    }

    @PatchMapping("/{companyId}/teams/{teamId}/projects/{projectId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProjectResponseDto editCompanyTeamProjectDescription(@RequestBody DescriptionDto descriptionDto,
        @PathVariable Long companyId, @PathVariable Long teamId, @PathVariable Long projectId) {
            return companyService.editCompanyTeamProjectDescription(descriptionDto, companyId, teamId, projectId);
    }

    @PatchMapping("/{companyId}/teams/{teamId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TeamResponseDto editTeam(@RequestBody DescriptionDto descriptionDto, @PathVariable Long companyId, @PathVariable Long teamId) {
        return companyService.editTeam(descriptionDto, companyId, teamId);
    }

    @DeleteMapping("/{companyId}/teams/{teamId}")
    public TeamResponseDto deleteTeamFromCompany(@PathVariable Long companyId, @PathVariable Long teamId){
        return companyService.deleteTeamFromCompany(companyId, teamId);
    }

    @DeleteMapping("/{companyId}/teams/{teamId}/projects/{projectId}")
    public ProjectResponseDto deleteProjectFromTeamFromCompany(@PathVariable Long companyId, @PathVariable Long teamId,
           @PathVariable Long projectId){
        return companyService.deleteProjectFromTeamFromCompany(companyId, teamId, projectId);
    }

}
