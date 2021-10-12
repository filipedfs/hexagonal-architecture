package com.filipefonseca.communication.adapters.outbound.persistence;


import com.filipefonseca.communication.adapters.outbound.persistence.entities.EmailEntity;
import com.filipefonseca.communication.application.domain.Email;
import com.filipefonseca.communication.application.domain.PageInfo;
import com.filipefonseca.communication.application.ports.EmailRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Primary
public class PostgresEmailRepository implements EmailRepositoryPort {
  private final SpringDataPostgresEmailRepository emailRepository;
  private final ModelMapper modelMapper;

  public PostgresEmailRepository(final SpringDataPostgresEmailRepository emailRepository,
                                 final ModelMapper modelMapper) {
    this.emailRepository = emailRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public Email save(Email email) {
    EmailEntity emailEntity = emailRepository.save(modelMapper.map(email, EmailEntity.class));
    return modelMapper.map(emailEntity, Email.class);
  }

  @Override
  public List<Email> findAll(PageInfo pageInfo) {
    Pageable pageable = PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize());
    return emailRepository.findAll(pageable).stream().map(entity -> modelMapper.map(entity, Email.class)).collect(Collectors.toList());
  }

  @Override
  public Optional<Email> findById(UUID emailId) {
    Optional<EmailEntity> emailEntity = emailRepository.findById(emailId);
    return emailEntity.map(entity -> modelMapper.map(entity, Email.class));
  }
}
