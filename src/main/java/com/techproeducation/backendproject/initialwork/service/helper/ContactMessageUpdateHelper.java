package com.techproeducation.backendproject.initialwork.service.helper;

import com.techproeducation.backendproject.initialwork.entity.ContactMessage;
import com.techproeducation.backendproject.initialwork.payload.request.ContactMessageRequest;
import org.springframework.stereotype.Component;


@Component
public class ContactMessageUpdateHelper {

    public void updateContactMessageFields(ContactMessage contactMessageDb, ContactMessageRequest contactMessageRequest) {
        if (contactMessageRequest.getName() != null && !contactMessageRequest.getName().isEmpty()) {
            contactMessageDb.setName(contactMessageRequest.getName());
        }
        if (contactMessageRequest.getEmail() != null && !contactMessageRequest.getEmail().isEmpty()) {
            contactMessageDb.setEmail(contactMessageRequest.getEmail());
        }
        if (contactMessageRequest.getSubject() != null && !contactMessageRequest.getSubject().isEmpty()) {
            contactMessageDb.setSubject(contactMessageRequest.getSubject());
        }
        if (contactMessageRequest.getMessage() != null && !contactMessageRequest.getMessage().isEmpty()) {
            contactMessageDb.setMessage(contactMessageRequest.getMessage());
        }
    }
}


