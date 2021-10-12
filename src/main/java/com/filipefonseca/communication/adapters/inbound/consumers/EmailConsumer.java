package com.filipefonseca.communication.adapters.inbound.consumers;

import com.filipefonseca.communication.adapters.inbound.dtos.EmailDto;
import com.filipefonseca.communication.application.entities.Email;
import com.filipefonseca.communication.application.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

  private final EmailService emailService;

  public EmailConsumer(EmailService emailService) {
    this.emailService = emailService;
  }

  @RabbitListener(queues = "${spring.rabbitmq.queue}")
  public void listen(
      @Payload
          EmailDto emailDto) {
    Email email = new Email();
    BeanUtils.copyProperties(emailDto, email);
    emailService.sendEmail(email);
    System.out.println("Email Status: " + email.getStatus().toString());
  }

}
