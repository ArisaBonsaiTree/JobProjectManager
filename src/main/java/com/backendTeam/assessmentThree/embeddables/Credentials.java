package com.backendTeam.assessmentThree.embeddables;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Data
public class Credentials {

//    @Column(name = "email", nullable = false, unique = true)
//    private String email;

    @Column(name = "password", nullable = false)
    private String password;
}
