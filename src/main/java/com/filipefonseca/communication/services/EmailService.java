package com.filipefonseca.communication.services;

import com.filipefonseca.communication.enums.EmailStatus;
import com.filipefonseca.communication.models.Email;
import com.filipefonseca.communication.repositories.EmailRepository;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {
  final EmailRepository emailRepository;

  final JavaMailSender emailSender;

  EmailService(final EmailRepository emailRepository, final JavaMailSender emailSender) {
    this.emailRepository = emailRepository;
    this.emailSender = emailSender;
  }

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
}
