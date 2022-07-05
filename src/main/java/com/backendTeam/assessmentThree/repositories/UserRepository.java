package com.backendTeam.assessmentThree.repositories;

import com.backendTeam.assessmentThree.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    List<User> findByCompanyId(Long company);



    Optional<User> findByEmail(String email);

    List<User> findAllByTeamId(Long id);
}
