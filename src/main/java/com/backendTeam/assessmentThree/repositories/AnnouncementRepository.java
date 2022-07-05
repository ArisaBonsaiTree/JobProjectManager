package com.backendTeam.assessmentThree.repositories;

import com.backendTeam.assessmentThree.entities.Announcements;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AnnouncementRepository extends JpaRepository<Announcements, Long> {

    List<Announcements> findByCompanyId(Long company);

}
