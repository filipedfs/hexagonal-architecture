package com.filipefonseca.communication.repositories;

import com.filipefonseca.communication.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
