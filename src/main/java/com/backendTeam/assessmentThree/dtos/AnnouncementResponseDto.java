package com.backendTeam.assessmentThree.dtos;

import java.sql.Timestamp;

import com.backendTeam.assessmentThree.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnnouncementResponseDto {
    
    private Timestamp date;
    private String title;
    private String message;

    private UserAnnouncementResponseDto user;

}
