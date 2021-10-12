package com.filipefonseca.communication.application.services;

import com.filipefonseca.communication.application.domain.Email;
import com.filipefonseca.communication.application.domain.PageInfo;
import com.filipefonseca.communication.application.domain.enums.EmailStatus;
import com.filipefonseca.communication.application.ports.EmailRepositoryPort;
import com.filipefonseca.communication.application.ports.EmailServicePort;
import com.filipefonseca.communication.application.ports.SendEmailServicePort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EmailService implements EmailServicePort {
  private final EmailRepositoryPort emailRepositoryPort;
  private final SendEmailServicePort sendEmailServicePort;

  public EmailService(final EmailRepositoryPort emailRepositoryPort, final SendEmailServicePort sendEmailServicePort) {
    this.emailRepositoryPort = emailRepositoryPort;
    this.sendEmailServicePort = sendEmailServicePort;
  }

  @Override
  public Email sendEmail(Email email) {
    email.setSentAt(LocalDateTime.now());

    try {
      sendEmailServicePort.sendEmailSmtp(email);
      email.setStatus(EmailStatus.SENT);
    } catch (Exception e) {
      email.setStatus(EmailStatus.FAILED);
    }

    return emailRepositoryPort.save(email);
  }

  @Override
  public List<Email> findAll(PageInfo pageInfo) {
    return emailRepositoryPort.findAll(pageInfo);
  }

  @Override
  public Optional<Email> findById(UUID emailId) {
    return emailRepositoryPort.findById(emailId);
  }
}
