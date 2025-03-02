package org.example.internintelligence_portfolio.repositories;

import org.example.internintelligence_portfolio.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
