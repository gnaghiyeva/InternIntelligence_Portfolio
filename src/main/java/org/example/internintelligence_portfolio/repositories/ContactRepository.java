package org.example.internintelligence_portfolio.repositories;

import org.example.internintelligence_portfolio.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
