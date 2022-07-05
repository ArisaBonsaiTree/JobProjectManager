package com.backendTeam.assessmentThree.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
public class Project {

    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    
    private String description;
    
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private Timestamp createdTime = Timestamp.valueOf(LocalDateTime.now());
    private Timestamp lastUpdated = Timestamp.valueOf(LocalDateTime.now());
}
