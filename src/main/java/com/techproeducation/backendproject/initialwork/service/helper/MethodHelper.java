package com.techproeducation.backendproject.initialwork.service.helper;

import com.techproeducation.backendproject.initialwork.entity.ContactMessage;
import com.techproeducation.backendproject.initialwork.exceptions.BadRequestException;
import com.techproeducation.backendproject.initialwork.exceptions.ResourceNotFoundException;
import com.techproeducation.backendproject.initialwork.mapper.ContactMessageMapper;
import com.techproeducation.backendproject.initialwork.repository.ContactMessage.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MethodHelper {

    private final ContactMessageRepository contactMessageRepository;
    private final ContactMessageMapper contactMessageMapper; // Mappingleme için ekledik.

    public ContactMessage isContactMessageExist(Long id) {
        return contactMessageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with ID: " + id));
    }

    public ContactMessage isContactMessageExistEmail(String email) {
        return contactMessageRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with email: " + email));
    }

    public void checkBuildIn(ContactMessage contactMessage) {
        if (contactMessage.getBuildIn() != null && contactMessage.getBuildIn()) {
            throw new IllegalStateException("This contact message cannot be updated.");
        }
    }



}






