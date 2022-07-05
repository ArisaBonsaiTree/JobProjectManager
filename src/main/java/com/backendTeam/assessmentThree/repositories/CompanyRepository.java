package com.backendTeam.assessmentThree.repositories;


import com.backendTeam.assessmentThree.dtos.ProjectResponseDto;
import com.backendTeam.assessmentThree.entities.Company;
import com.backendTeam.assessmentThree.entities.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	
}
