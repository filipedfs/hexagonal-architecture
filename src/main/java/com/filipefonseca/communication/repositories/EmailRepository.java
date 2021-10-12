package com.filipefonseca.communication.repositories;

import com.filipefonseca.communication.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {}
