package com.backendTeam.assessmentThree.dtos;

import com.backendTeam.assessmentThree.dtos.CredentialsDto;
import com.backendTeam.assessmentThree.entities.Company;

import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
public class AnnouncementRequestDto {

    private CredentialsDto credentials;
    private String title;
    private String message;
    private Company company;
}
