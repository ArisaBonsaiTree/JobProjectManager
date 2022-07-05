package com.backendTeam.assessmentThree.dtos;

import com.backendTeam.assessmentThree.dtos.CredentialsDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectRequestDto {
    private CredentialsDto credentials;
    private String name;
    private String description;
}
