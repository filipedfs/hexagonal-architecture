package com.filipefonseca.communication.adapters.configuration;

import com.filipefonseca.communication.CommunicationApplication;
import com.filipefonseca.communication.application.ports.EmailRepositoryPort;
import com.filipefonseca.communication.application.ports.SendEmailServicePort;
import com.filipefonseca.communication.application.services.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = CommunicationApplication.class)
public class BeanConfiguration {
  @Bean
  EmailService emailService(EmailRepositoryPort repository, SendEmailServicePort sendEmailServicePort) {
    return new EmailService(repository, sendEmailServicePort);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
