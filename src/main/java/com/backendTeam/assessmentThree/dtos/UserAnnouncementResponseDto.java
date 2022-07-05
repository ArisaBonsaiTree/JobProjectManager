package com.backendTeam.assessmentThree.dtos;

import com.backendTeam.assessmentThree.embeddables.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAnnouncementResponseDto {
    private Profile profile;
}
