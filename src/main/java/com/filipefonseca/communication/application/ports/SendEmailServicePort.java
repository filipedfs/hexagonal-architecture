package com.filipefonseca.communication.application.ports;

import com.filipefonseca.communication.application.domain.Email;

public interface SendEmailServicePort {
  void sendEmailSmtp(Email email);
}
