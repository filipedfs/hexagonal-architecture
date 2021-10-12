package com.filipefonseca.communication.models;

import com.filipefonseca.communication.enums.EmailStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "emails")
public class Email implements Serializable {

    @Serial
    private static final long serialVersionUID = 2647004771307371393L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sentAt;
    private EmailStatus status;
}