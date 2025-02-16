package com.techproeducation.backendproject.initialwork.service;

import com.techproeducation.backendproject.initialwork.entity.ContactMessage;
import com.techproeducation.backendproject.initialwork.exceptions.ConflictException;
import com.techproeducation.backendproject.initialwork.payload.request.ContactMessageRequest;
import com.techproeducation.backendproject.initialwork.repository.ContactMessage.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueValidator {

    private final ContactMessageRepository contactMessageRepository;

    public void checkUniqueProperty(ContactMessage contactMessage, ContactMessageRequest contactMessageRequest){

        String updatedEmail = "";


        boolean isChanged = false;
        //we are checking that if we change the unique prop.s
        if(!contactMessage.getEmail().equals(contactMessage.getEmail())) {
            updatedEmail = contactMessageRequest.getEmail();
            isChanged = true;
        }
        if(isChanged){
            checkDuplication(updatedEmail);
        }
    }

    public void checkDuplication(String email) {
        if (contactMessageRepository.existsByEmail(email)) {
            throw new ConflictException("Email allready exist!: " + email);
        }
    }

}
