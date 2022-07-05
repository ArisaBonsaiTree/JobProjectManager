package com.backendTeam.assessmentThree.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

@Entity
@NoArgsConstructor
@Data
public class Announcements {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @CreatedDate
    private Timestamp date = Timestamp.valueOf(LocalDateTime.now());

    @Column
    private String title;

    @Column
    private String message;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
