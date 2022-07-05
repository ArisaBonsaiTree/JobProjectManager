package com.backendTeam.assessmentThree.repositories;

import com.backendTeam.assessmentThree.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByTeamId(Long id);

    Optional<Project> findById(Long id);
}





