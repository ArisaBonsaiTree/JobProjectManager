package com.backendTeam.assessmentThree.services.Impl;

import com.backendTeam.assessmentThree.dtos.CredentialsDto;
import com.backendTeam.assessmentThree.dtos.UserLoginResponseDto;
import com.backendTeam.assessmentThree.dtos.UserRequestDto;
import com.backendTeam.assessmentThree.dtos.UserResponseDto;
import com.backendTeam.assessmentThree.entities.Company;
import com.backendTeam.assessmentThree.entities.Team;
import com.backendTeam.assessmentThree.entities.User;
import com.backendTeam.assessmentThree.exceptions.BadRequestException;
import com.backendTeam.assessmentThree.exceptions.NotAuthorizedException;
import com.backendTeam.assessmentThree.exceptions.NotFoundException;
import com.backendTeam.assessmentThree.mappers.CompanyMapper;
import com.backendTeam.assessmentThree.mappers.TeamMapper;
import com.backendTeam.assessmentThree.mappers.UserMapper;
import com.backendTeam.assessmentThree.repositories.CompanyRepository;
import com.backendTeam.assessmentThree.repositories.TeamRepository;
import com.backendTeam.assessmentThree.repositories.UserRepository;
import com.backendTeam.assessmentThree.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class    UserServiceImpl implements UserService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private User checkToSeeIfUserExist(String email, String password) {
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
    public List<UserResponseDto> getAllUsers() {
        return userMapper.entitiesToDtos(userRepository.findAll());
    }

    @Override
    public UserLoginResponseDto getUserByCredentials(CredentialsDto credentials) {
        return userMapper.entityToDto(checkToSeeIfUserExist(credentials.getEmail(), credentials.getPassword()));
    }

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        Optional<User> findUserEmail = userRepository.findByEmail(userRequestDto.getEmail());
        if(findUserEmail.isPresent()){
            throw new BadRequestException("A user with this email already exist!");
        }

        // get the company
        Optional<Company> getCompany = companyRepository.findById(userRequestDto.getCompanyId());
        if(getCompany.isEmpty()){
            throw new NotFoundException("Company with this ID " + userRequestDto.getCompanyId() + " does not exist!");
        }


        // get the team
        Optional< Team> getTeam = teamRepository.findById(userRequestDto.getTeamId());
        if(getTeam.isEmpty()){
            throw new NotFoundException("Team with this ID " + userRequestDto.getTeamId() + " does not exist");
        }
        if(getTeam.get().getCompany().getId() != userRequestDto.getCompanyId()){
            throw new NotFoundException("Team does not belong to this company!");
        }


        User userToSave = userMapper.requestDtoToEntity(userRequestDto);

        userToSave.setEmail(userRequestDto.getEmail());

        userToSave.getCredentials().setPassword(userRequestDto.getCredentials().getPassword());



        userToSave.setAdmin(userRequestDto.getAdmin());
        userToSave.setActive(userRequestDto.getActive());
        userToSave.setTeam(getTeam.get());
        userToSave.setCompany(getCompany.get());

        return userMapper.entityToDtos(userRepository.saveAndFlush(userToSave));
    }
}