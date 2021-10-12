package com.filipefonseca.communication.application.ports;

import com.filipefonseca.communication.application.entities.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface EmailRepositoryInterface {
  Email save(Email email);

  Page<Email> findAll(Pageable pageable);

  Optional<Email> findById(UUID emailId);
}
