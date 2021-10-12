package com.filipefonseca.communication.adapters.outbound.smtp;

import com.filipefonseca.communication.application.domain.Email;
import com.filipefonseca.communication.application.ports.SendEmailServicePort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SmtpSendEmailService implements SendEmailServicePort {
  private final JavaMailSender emailSender;

  public SmtpSendEmailService(JavaMailSender emailSender) {
    this.emailSender = emailSender;
  }

  @Override
  public void sendEmailSmtp(Email email) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(email.getEmailFrom());
    message.setTo(email.getEmailTo());
    message.setSubject(email.getSubject());
    message.setText(email.getText());
    emailSender.send(message);
  }
}
