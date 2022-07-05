package com.backendTeam.assessmentThree.dtos;

import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@NoArgsConstructor

public class TeamRequestDto {
    
   private CredentialsDto credentials;
   private String name;
   private String description;
   private List<BasicUserDto> members;
}
