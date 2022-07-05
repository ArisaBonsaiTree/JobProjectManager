package com.backendTeam.assessmentThree.services.Impl;

import com.backendTeam.assessmentThree.dtos.*;
import com.backendTeam.assessmentThree.entities.Announcements;
import com.backendTeam.assessmentThree.entities.Company;
import com.backendTeam.assessmentThree.entities.Project;
import com.backendTeam.assessmentThree.entities.Team;
import com.backendTeam.assessmentThree.entities.User;
import com.backendTeam.assessmentThree.exceptions.BadRequestException;
import com.backendTeam.assessmentThree.exceptions.NotAuthorizedException;
import com.backendTeam.assessmentThree.exceptions.NotFoundException;
import com.backendTeam.assessmentThree.mappers.*;
import com.backendTeam.assessmentThree.repositories.*;
import com.backendTeam.assessmentThree.services.CompanyService;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;

    @Override
    public List<CompanyResponseDto> getAllCompanies() {
        return companyMapper.entitiesToResponseDtos(companyRepository.findAll());
    }

    @Override
    public List<UserResponseDto> getUsersFromCompany(Long id) {
        return userMapper.entitiesToDtos(userRepository.findByCompanyId(id));
    }

    @Override
    public List<UserResponseDto> getUsersFromTeamUnderCompany(Long companyId, Long teamId) {
        // Check to see if the company exist
        Optional<Company> company = companyRepository.findById(companyId);
        if(company.isEmpty()){
            throw new NotFoundException("Company with this ID " + companyId + " does not exist!");
        }

        Optional<Team> checkIfTeamBelongsToCompany = teamRepository.findById(teamId);
        if(checkIfTeamBelongsToCompany.isEmpty()){
            throw new NotFoundException("Team with this ID " + teamId + " does not exist!");
        }

        if(checkIfTeamBelongsToCompany.get().getCompany().getId() != companyId){
            throw new BadRequestException("Team is not listed under this companyId " + companyId);
        }



        return userMapper.entitiesToDtos(userRepository.findAllByTeamId(teamId));
    }

    @Override
    public List<TeamResponseDto> getTeamsFromCompany(Long id) {
        return teamMapper.entitiesToDtos(teamRepository.findByCompanyId(id));
    }

    @Override
    public List<AnnouncementResponseDto> getAnnouncementsFromCompany(Long id) {
        return announcementMapper.entitiesToDtos(announcementRepository.findByCompanyId(id));
    }

    private void checkToSeeIfUserExist(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User with this email doesn't exist: " + email);
        }

        if (!optionalUser.get().getCredentials().getPassword().equals(password) ||
                !optionalUser.get().getEmail().equals(email)) {
            throw new NotAuthorizedException("Email or password isn't correct");
        }

    }

    private User checkToSeeIfUserExist(String email, String password, boolean wantUser) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User with this email doesn't exist: " + email);
        }

        if (!optionalUser.get().getCredentials().getPassword().equals(password) ||
                !optionalUser.get().getEmail().equals(email)) {
            throw new NotAuthorizedException("Email or password isn't correct");
        }

        return optionalUser.get();

    }

    @Override
    public CompanyResponseDto createCompany(CompanyRequestDto companyRequestDto) {
        if (companyRequestDto.getCredentials() == null ||
                companyRequestDto.getCredentials().getEmail() == null ||
                companyRequestDto.getCredentials().getPassword() == null) {
            throw new NotAuthorizedException("Missing credentials");
        }

        checkToSeeIfUserExist(companyRequestDto.getCredentials().getEmail(),
                companyRequestDto.getCredentials().getPassword());

        if (companyRequestDto.getName() == null || companyRequestDto.getDescription() == null) {
            throw new NotFoundException("Missing name or description of the company");
        }

        Company companyToSave = companyMapper.requestDtoToEntity(companyRequestDto);

        companyToSave.setName(companyRequestDto.getName());
        companyToSave.setDescription(companyRequestDto.getDescription());

        companyRepository.saveAndFlush(companyToSave);

        return companyMapper.entityToDto(companyRepository.saveAndFlush(companyToSave));
    }

    @Override
    public List<ProjectResponseDto> getProjectsByTeamByCompany(Long id, Long secondId) {

        List<Team> optionalCompany = teamRepository.findByCompanyId(id);
        if (optionalCompany.isEmpty()) {
            throw new NotFoundException("Team or company with this id does not exist");
        }

        Optional<Team> checkIfTeamBelongsToCompany = teamRepository.findById(secondId);
        if (checkIfTeamBelongsToCompany.get().getCompany().getId() != id) {
            throw new NotFoundException("Team does not belong to this company!");
        }

        List<Project> listOfProjects = projectRepository.findAllByTeamId(secondId);
        if (listOfProjects.isEmpty()) {
            return Collections.emptyList();
        }

        return projectMapper.entitiesToDtos(listOfProjects);
    }

    @Override
    public ProjectResponseDto editCompanyTeamProjectDescription(DescriptionDto descriptionDto, Long companyId,
            Long teamId, Long projectId) {
        if (descriptionDto == null || descriptionDto.getDescription() == null) {
            throw new BadRequestException("Missing description");
        }

        Optional<Team> checkIfTeamBelongsToCompany = teamRepository.findById(teamId);
        if (checkIfTeamBelongsToCompany.get().getCompany().getId() != companyId) {
            throw new NotFoundException("Team does not belong to this company!");
        }

        List<Team> optionalCompany = teamRepository.findByCompanyId(companyId);

        if (optionalCompany.isEmpty()) {
            throw new NotFoundException("Team with this ID does not exist");
        }
        Optional<Project> projectToEdit = projectRepository.findById(projectId);
        if (projectToEdit.isEmpty()) {
            throw new NotFoundException("Project with this ID does not exist");
        }

        Project projectToUpdate = projectToEdit.get();

        projectToUpdate.setDescription(descriptionDto.getDescription());
        projectToUpdate.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));

        return projectMapper.entityToDto(projectRepository.saveAndFlush(projectToUpdate));

    }

    @Override
    public TeamResponseDto editTeam(DescriptionDto descriptionDto, Long companyId, Long teamId) {
        if (descriptionDto == null || descriptionDto.getDescription() == null) {
            throw new BadRequestException("Missing description");
        }

        Optional<Team> checkIfTeamBelongsToCompany = teamRepository.findById(teamId);
        if (checkIfTeamBelongsToCompany.get().getCompany().getId() != companyId) {
            throw new NotFoundException("Team does not belong to this company!");
        }

        List<Team> optionalCompany = teamRepository.findByCompanyId(companyId);

        if (optionalCompany.isEmpty()) {
            throw new NotFoundException("Team with this ID does not exist");
        }

        Optional<Team> teamToEdit = teamRepository.findById(teamId);
        if (teamToEdit.isEmpty()) {
            throw new NotFoundException("Team with this ID does not exist");
        }

        Team teamEdit = teamToEdit.get();

        teamEdit.setDescription(descriptionDto.getDescription());



        return teamMapper.entityToDto(teamRepository.saveAndFlush(teamEdit));
    }

    @Override
    public TeamResponseDto createTeamForCompany(TeamRequestDto teamRequestDto, Long companyId) {
        if (teamRequestDto.getCredentials() == null ||
                teamRequestDto.getCredentials().getEmail() == null ||
                teamRequestDto.getCredentials().getPassword() == null

        ) {
            throw new NotAuthorizedException("Missing credentials");
        }

        checkToSeeIfUserExist(teamRequestDto.getCredentials().getEmail(),
                teamRequestDto.getCredentials().getPassword());

        Optional<Company> optionalCompany = companyRepository.findById(companyId);

        if (optionalCompany.isEmpty()) {
            throw new NotFoundException("Company with this ID does not exist");
        }
        if (teamRequestDto.getName() == null || teamRequestDto.getDescription() == null) {
            throw new NotFoundException("Missing name or description of team");
        }

        Team team = teamMapper.requestDtoToEntity(teamRequestDto);

        team.setName(teamRequestDto.getName());
        team.setDescription(teamRequestDto.getDescription());
        team.setCompany(optionalCompany.get());
        
        for(int i = 0; i < teamRequestDto.getMembers().size(); i++){
            Optional<User> userExist = userRepository.findById(teamRequestDto.getMembers().get(i).getId());
            if(!userExist.isEmpty()){
                userExist.get().setTeam(team);
//                userRepository.saveAndFlush(userExist.get());
            }
        }
        
        teamRepository.saveAndFlush(team);
        return teamMapper.entityToDto(teamRepository.saveAndFlush(team));

    }

    @Override
    public ProjectResponseDto createProjectForTeamAndCompany(ProjectRequestDto projectRequestDto, Long teamId,
            Long companyId) {

        if (projectRequestDto.getCredentials() == null ||
                projectRequestDto.getCredentials().getEmail() == null ||
                projectRequestDto.getCredentials().getPassword() == null) {
            throw new NotAuthorizedException("Missing credentials");
        }

        checkToSeeIfUserExist(projectRequestDto.getCredentials().getEmail(),
                projectRequestDto.getCredentials().getPassword());

        Optional<Company> optionalCompany = companyRepository.findById(companyId);

        if (optionalCompany.isEmpty()) {
            throw new NotFoundException("Company with this ID does not exist");
        }

        Optional<Team> optionalTeam = teamRepository.findById(teamId);

        if (optionalTeam.isEmpty()) {
            throw new NotFoundException("Team with this ID does not exist");
        }

        if (projectRequestDto.getName() == null || projectRequestDto.getDescription() == null) {
            throw new NotFoundException("Missing name or description of team");
        }

        Project project = projectMapper.requestDtoToEntity(projectRequestDto);

        project.setName(projectRequestDto.getName());
        project.setDescription(projectRequestDto.getDescription());
        project.setActive(true);
        project.setTeam(optionalTeam.get());

        projectRepository.saveAndFlush(project);
        return projectMapper.entityToDto(projectRepository.saveAndFlush(project));

    }

    @Override
    public AnnouncementResponseDto createAnnouncementForCompany(AnnouncementRequestDto announcementRequestDto,
            Long companyId) {

        if (announcementRequestDto.getCredentials() == null ||
                announcementRequestDto.getCredentials().getEmail() == null ||
                announcementRequestDto.getCredentials().getPassword() == null) {
            throw new NotAuthorizedException("Missing credentials");
        }

        User user = checkToSeeIfUserExist(announcementRequestDto.getCredentials().getEmail(),
                announcementRequestDto.getCredentials().getPassword(), true);


        Optional<Company> optionalCompany = companyRepository.findById(companyId);

        if (optionalCompany.isEmpty()) {
            throw new NotFoundException("Company with this ID does not exist");
        }

        Announcements announcement = announcementMapper.requestDtoToEntity(announcementRequestDto);

        announcement.setTitle(announcementRequestDto.getTitle());
        announcement.setMessage(announcementRequestDto.getMessage());
        announcement.setCompany(announcementRequestDto.getCompany());
        announcement.setUser(user);

        announcementRepository.saveAndFlush(announcement);
        return announcementMapper.entityToDto(announcementRepository.saveAndFlush(announcement));
    }

    public Team getTeam(Long id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isEmpty()) {
            throw new NotFoundException("No team with this ID exist");
        }
        return optionalTeam.get();
    }

    @Override
    public TeamResponseDto deleteTeamFromCompany(Long companyId, Long teamId) {
        if (companyId == null || teamId == null) {
            throw new BadRequestException("Missing companyId or teamId");
        }

        Team teamToDelete = getTeam(teamId);

        if (teamToDelete.getCompany().getId() != companyId) {
            throw new NotFoundException("Team does not belong to this company!");
        }

        // Remove Users with the Team Id
        List<User> usersWithTeamId = userRepository.findAllByTeamId(teamId);
        if (!usersWithTeamId.isEmpty()) {
            for (User u : usersWithTeamId) {
                u.setTeam(null);
            }
        }
        userRepository.saveAllAndFlush(usersWithTeamId);

        // Remove projects with the Team Id
        List<Project> projectsWithTeamId = projectRepository.findAllByTeamId(teamId);
        if (!projectsWithTeamId.isEmpty()) {
            for (Project p : projectsWithTeamId) {
                p.setTeam(null);
            }
        }

        projectRepository.saveAllAndFlush(projectsWithTeamId);

        // Finally Delete the team
        teamRepository.delete(teamToDelete);

        return teamMapper.entityToDto(teamToDelete);
    }

    @Override
    public ProjectResponseDto deleteProjectFromTeamFromCompany(Long companyId, Long teamId, Long projectId) {
        if (companyId == null || teamId == null || projectId == null) {
            throw new BadRequestException("Missing companyId, teamId, or projectId");
        }

        Team teamToDelete = getTeam(teamId);

        if (teamToDelete.getCompany().getId() != companyId) {
            throw new NotFoundException("Team does not belong to this company!");
        }

        Optional<Project> projectToDelete = projectRepository.findById(projectId);
        if (projectToDelete.isEmpty()) {
            throw new NotFoundException("Project with this ID does not exist");
        }

        if (projectToDelete.get().getTeam().getId() != teamId) {
            throw new NotFoundException("Project does not belong to this team");
        }

        projectRepository.delete(projectToDelete.get());

        return projectMapper.entityToDto(projectToDelete.get());

    }

}