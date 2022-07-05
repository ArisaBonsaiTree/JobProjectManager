package com.backendTeam.assessmentThree.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {
    private String email;
    private ProfileDto profile;

    private boolean active;
    private boolean admin;
    private String status;

    private UserTeamResponseDto team;





}
