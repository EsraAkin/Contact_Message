package com.techproeducation.backendproject.initialwork.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_contactMessage")
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Boolean buildIn;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(unique=true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "US/Central")
    @Column(name = "local_date_time", updatable = false)
    @CreationTimestamp
    private LocalDateTime localDateTime;
}
