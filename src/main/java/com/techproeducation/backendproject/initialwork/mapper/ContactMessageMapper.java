package com.techproeducation.backendproject.initialwork.mapper;

import com.techproeducation.backendproject.initialwork.entity.ContactMessage;
import com.techproeducation.backendproject.initialwork.payload.request.ContactMessageRequest;
import com.techproeducation.backendproject.initialwork.payload.response.ContactMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactMessageMapper {



    public ContactMessage mapContactMessageRequestToContactMessage(ContactMessageRequest contactMessageRequest){
        ContactMessage contactMessage=ContactMessage.builder()
                .message(contactMessageRequest.getMessage())
                .subject(contactMessageRequest.getSubject())
                .eMail(contactMessageRequest.getEMail())
                .localDateTime(contactMessageRequest.getLocalDateTime())
                .name(contactMessageRequest.getName())
                .build();
        return contactMessage;

    }

public ContactMessageResponse mapContactMessageToContactMessageResponse(ContactMessage contactMessage){
  return ContactMessageResponse.builder()
          .id(contactMessage.getId())
           .message(contactMessage.getMessage())
           .subject(contactMessage.getSubject())
           .name(contactMessage.getName())
           .eMail(contactMessage.getEMail())
           .localDateTime(contactMessage.getLocalDateTime())
           .build();

}

}
