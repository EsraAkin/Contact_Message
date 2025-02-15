package com.techproeducation.backendproject.initialwork.service;

import com.techproeducation.backendproject.initialwork.entity.ContactMessage;
import com.techproeducation.backendproject.initialwork.exceptions.ResourceNotFoundException;
import com.techproeducation.backendproject.initialwork.mapper.ContactMessageMapper;
import com.techproeducation.backendproject.initialwork.payload.response.ContactMessageResponse;
import com.techproeducation.backendproject.initialwork.repository.ContactMessage.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

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



}






