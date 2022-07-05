package com.backendTeam.assessmentThree.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class ProjectResponseDto {
    private Long id;
    private String name;
    private String description;

    private Timestamp createdTime;
    private Timestamp lastUpdated;
}
