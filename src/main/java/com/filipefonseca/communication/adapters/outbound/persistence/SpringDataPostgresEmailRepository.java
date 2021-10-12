package com.filipefonseca.communication.adapters.outbound.persistence;

import com.filipefonseca.communication.adapters.outbound.persistence.entities.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataPostgresEmailRepository extends JpaRepository<EmailEntity, UUID> {}
