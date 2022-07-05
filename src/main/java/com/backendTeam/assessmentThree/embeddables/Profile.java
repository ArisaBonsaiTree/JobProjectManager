package com.backendTeam.assessmentThree.embeddables;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Data
public class Profile {
    @Column(name= "first_name")
    private String firstName;

    @Column(name= "last_name")
    private String lastName;

//    @Column(name= "email", nullable = false)
//    private String email;

    @Column(name= "phone_number")
    private String phone; 
}
