package com.backendTeam.assessmentThree.entities;

import com.backendTeam.assessmentThree.embeddables.Credentials;
import com.backendTeam.assessmentThree.embeddables.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_table")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;


    private String email;
//     email and password
    @Embedded
    private Credentials credentials;

//     firstName, lastName, and phone[_number]
    @Embedded
    private Profile profile;

    private boolean active;
    private boolean admin;
    private String status;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;



}
