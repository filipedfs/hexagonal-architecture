package com.filipefonseca.communication.controllers;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.filipefonseca.communication.dtos.EmailDto;
import com.filipefonseca.communication.models.Email;
import com.filipefonseca.communication.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailController {
  final EmailService emailService;

  EmailController(final EmailService emailService) {
    this.emailService = emailService;
  }

  @PostMapping("/send-email")
  public ResponseEntity<Email> sendEmail(@RequestBody @Valid EmailDto emailDto) {
    Email email = new Email();
    BeanUtils.copyProperties(emailDto, email);
    email = emailService.sendEmail(email);
    return new ResponseEntity<>(email, HttpStatus.CREATED);
  }
}
