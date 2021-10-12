package com.filipefonseca.communication.adapters.inbound.controllers;

import com.filipefonseca.communication.adapters.dtos.EmailDto;
import com.filipefonseca.communication.application.domain.Email;
import com.filipefonseca.communication.application.domain.PageInfo;
import com.filipefonseca.communication.application.ports.EmailServicePort;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class EmailController {
  private final EmailServicePort emailServicePort;

  EmailController(final EmailServicePort emailServicePort) {
    this.emailServicePort = emailServicePort;
  }

  @PostMapping("/send-email")
  public ResponseEntity<Email> sendEmail(
      @RequestBody
      @Valid EmailDto emailDto) {
    Email email = new Email();
    BeanUtils.copyProperties(emailDto, email);
    return new ResponseEntity<>(emailServicePort.sendEmail(email), HttpStatus.CREATED);
  }

  @GetMapping("/emails")
  public ResponseEntity<Page<Email>> getAllEmails(
      @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC)
          Pageable pageable) {
    final PageInfo pageInfo = new PageInfo();
    BeanUtils.copyProperties(pageable, pageInfo);
    final List<Email> emails = emailServicePort.findAll(pageInfo);
    return new ResponseEntity<>(new PageImpl<>(emails, pageable, emails.size()), HttpStatus.OK);
  }

  @GetMapping("/emails/{emailId}")
  public ResponseEntity<Object> getOneEmail(
      @PathVariable(value = "emailId")
          UUID emailId) {
    Optional<Email> email = emailServicePort.findById(emailId);
    if (email.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
    } else {
      return ResponseEntity.status(HttpStatus.OK).body(email.get());
    }
  }
}
