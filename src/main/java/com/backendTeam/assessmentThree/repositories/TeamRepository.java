package com.backendTeam.assessmentThree.repositories;

import com.backendTeam.assessmentThree.entities.Team;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByCompanyId(Long company);

    Optional<Team> findById(Long companyId);


}
