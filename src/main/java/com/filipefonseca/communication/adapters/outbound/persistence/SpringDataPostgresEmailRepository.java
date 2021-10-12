package com.filipefonseca.communication.adapters.outbound.persistence;

import com.filipefonseca.communication.application.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataPostgresEmailRepository extends JpaRepository<Email, UUID> {}
