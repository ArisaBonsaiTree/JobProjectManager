package com.backendTeam.assessmentThree.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegistryDto {
    private Long id;
    private String name;
    private String email;
    private String team;
    private Boolean active;
    private Boolean admin;
    private String status;
}
