package com.filipefonseca.communication.adapters.configuration;

import com.filipefonseca.communication.CommunicationApplication;
import com.filipefonseca.communication.application.ports.EmailRepositoryInterface;
import com.filipefonseca.communication.application.services.EmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
@ComponentScan(basePackageClasses = CommunicationApplication.class)
public class BeanConfiguration {
  @Bean
  EmailService emailService(EmailRepositoryInterface repository, JavaMailSender emailSender) {
    return new EmailService(repository, emailSender);
  }
}
