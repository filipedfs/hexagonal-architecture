package com.filipefonseca.communication.application.entities;

import com.filipefonseca.communication.application.entities.enums.EmailStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "emails")
public class Email implements Serializable {

    @Serial
    private static final long serialVersionUID = 2647004771307371393L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sentAt;
    private EmailStatus status;
}
