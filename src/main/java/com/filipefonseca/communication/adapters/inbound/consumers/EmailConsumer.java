package com.filipefonseca.communication.adapters.inbound.consumers;

import com.filipefonseca.communication.adapters.dtos.EmailDto;
import com.filipefonseca.communication.application.domain.Email;
import com.filipefonseca.communication.application.ports.EmailServicePort;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

  private final EmailServicePort emailServicePort;

  public EmailConsumer(EmailServicePort emailServicePort) {
    this.emailServicePort = emailServicePort;
  }

  @RabbitListener(queues = "${spring.rabbitmq.queue}")
  public void listen(
      @Payload
          EmailDto emailDto) {
    Email email = new Email();
    BeanUtils.copyProperties(emailDto, email);
    emailServicePort.sendEmail(email);
    System.out.println("Email Status: " + email.getStatus().toString());
  }

}
