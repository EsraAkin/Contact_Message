package com.techproeducation.backendproject.initialwork.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techproeducation.backendproject.initialwork.payload.dto.ContactMessageDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactMessageResponse extends ContactMessageDTO {

    //private Long id;
    private String name;
    private String eMail;
    private String subject;
    private String message;
    private LocalDateTime localDateTime;
}
