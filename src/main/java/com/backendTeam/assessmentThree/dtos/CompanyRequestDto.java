package com.backendTeam.assessmentThree.dtos;

import com.backendTeam.assessmentThree.dtos.CredentialsDto;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
public class CompanyRequestDto {
    
    private CredentialsDto credentials;
    private String name;
    private String description;
}
