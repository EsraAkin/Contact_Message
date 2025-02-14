package com.techproeducation.backendproject.initialwork.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ContactMessageDTO {


    @NotNull(message = "Please enter your name")
    private String name;

    @Email
    @NotNull(message = "Please enter your eMail")
    private String eMail;

    @NotNull(message = "Please enter subject")
    private String subject;

    @NotNull(message = "Please enter your message")
    @Size(min = 4, max =1200)
    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "US/Central")
    private LocalDateTime localDateTime;

}
