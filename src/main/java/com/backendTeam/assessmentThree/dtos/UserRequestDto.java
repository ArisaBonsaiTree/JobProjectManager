package com.backendTeam.assessmentThree.dtos;

import com.backendTeam.assessmentThree.embeddables.Credentials;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserRequestDto {

    private String email;

    private Credentials credentials;

    private ProfileDto profile;

    private Boolean active;
    private Boolean admin;
    private String status;

    private Long companyId;
    private Long teamId;




}
