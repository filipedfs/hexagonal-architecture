package com.filipefonseca.communication.adapters.inbound.controllers;

import com.filipefonseca.communication.adapters.inbound.dtos.EmailDto;
import com.filipefonseca.communication.application.entities.Email;
import com.filipefonseca.communication.application.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
public class EmailController {
  private final EmailService emailService;

  EmailController(final EmailService emailService) {
    this.emailService = emailService;
  }

  @PostMapping("/send-email")
  public ResponseEntity<Email> sendEmail(
      @RequestBody
      @Valid EmailDto emailDto) {
    Email email = new Email();
    BeanUtils.copyProperties(emailDto, email);
    emailService.sendEmail(email);
    return new ResponseEntity<>(email, HttpStatus.CREATED);
  }

  @GetMapping("/emails")
  public ResponseEntity<Page<Email>> getAllEmails(
      @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC)
          Pageable pageable) {
    return new ResponseEntity<>(emailService.findAll(pageable), HttpStatus.OK);
  }

  @GetMapping("/emails/{emailId}")
  public ResponseEntity<Object> getOneEmail(
      @PathVariable(value = "emailId")
          UUID emailId) {
    Optional<Email> email = emailService.findById(emailId);
    if (email.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
    } else {
      return ResponseEntity.status(HttpStatus.OK).body(email.get());
    }
  }
}
