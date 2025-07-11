package com.codeviewandtalk.library.management.repository;

import com.codeviewandtalk.library.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Additional query methods can be defined here if needed
    Optional<User> findByEmail(String email);

}
