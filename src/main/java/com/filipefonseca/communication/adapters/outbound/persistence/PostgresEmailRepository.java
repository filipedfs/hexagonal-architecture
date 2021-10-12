package com.filipefonseca.communication.adapters.outbound.persistence;


import com.filipefonseca.communication.application.entities.Email;
import com.filipefonseca.communication.application.ports.EmailRepositoryInterface;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@Primary
public class PostgresEmailRepository implements EmailRepositoryInterface {
  private final SpringDataPostgresEmailRepository emailRepository;

  public PostgresEmailRepository(final SpringDataPostgresEmailRepository emailRepository) {
    this.emailRepository = emailRepository;
  }

  @Override
  public Email save(Email email) {
    return this.emailRepository.save(email);
  }

  @Override
  public Page<Email> findAll(Pageable pageable) {
    return this.emailRepository.findAll(pageable);
  }

  @Override
  public Optional<Email> findById(UUID emailId) {
    return this.emailRepository.findById(emailId);
  }
}
