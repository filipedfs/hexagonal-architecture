package com.filipefonseca.communication.adapters.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {
  @Value("${spring.rabbitmq.queue}")
  private String queue;

  @Value("${spring.rabbitmq.host}")
  private String host;

  @Value("${spring.rabbitmq.port}")
  private int port;

  @Value("${spring.rabbitmq.password}")
  private String password;

  @Value("${spring.rabbitmq.username}")
  private String username;

  @Bean
  public ConnectionFactory rabbitConnectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
    connectionFactory.setUsername(username);
    connectionFactory.setHost(host);
    connectionFactory.setPassword(password);
    connectionFactory.setPort(port);
    return connectionFactory;
  }

  @Bean
  public Queue queue() {
    return new Queue(queue, true);
  }

  @Bean
  public Jackson2JsonMessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
