package com.backendTeam.assessmentThree.dtos;

import com.backendTeam.assessmentThree.embeddables.Credentials;
import com.backendTeam.assessmentThree.embeddables.Profile;
import com.backendTeam.assessmentThree.entities.Company;
import com.backendTeam.assessmentThree.entities.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserLoginResponseDto {
    private Long id;
    private String email;
    private Credentials credentials;
    private Profile profile;
    private boolean active;
    private boolean admin;
    private String status;
}
