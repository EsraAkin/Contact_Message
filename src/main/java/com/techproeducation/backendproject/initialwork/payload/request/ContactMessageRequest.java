package com.techproeducation.backendproject.initialwork.payload.request;

import com.techproeducation.backendproject.initialwork.payload.dto.ContactMessageDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder

public class ContactMessageRequest extends ContactMessageDTO {
}
