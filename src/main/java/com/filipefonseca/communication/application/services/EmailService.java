package com.filipefonseca.communication.application.services;

import com.filipefonseca.communication.application.entities.Email;
import com.filipefonseca.communication.application.entities.enums.EmailStatus;
import com.filipefonseca.communication.application.ports.EmailRepositoryInterface;
import com.filipefonseca.communication.application.ports.EmailServiceInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class EmailService implements EmailServiceInterface {
  private final EmailRepositoryInterface emailRepository;

  private final JavaMailSender emailSender;

  public EmailService(final EmailRepositoryInterface emailRepository, final JavaMailSender emailSender) {
    this.emailRepository = emailRepository;
    this.emailSender = emailSender;
  }

  @Override
  public Email sendEmail(Email email) {
    email.setSentAt(LocalDateTime.now());

    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom(email.getEmailFrom());
      message.setTo(email.getEmailTo());
      message.setSubject(email.getSubject());
      message.setText(email.getText());
      emailSender.send(message);
      email.setStatus(EmailStatus.SENT);
    } catch (MailException e) {
      email.setStatus(EmailStatus.FAILED);
    }

    return emailRepository.save(email);

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
