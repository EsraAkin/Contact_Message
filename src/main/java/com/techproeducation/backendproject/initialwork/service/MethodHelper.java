package com.techproeducation.backendproject.initialwork.service;

import com.techproeducation.backendproject.initialwork.entity.ContactMessage;
import com.techproeducation.backendproject.initialwork.exceptions.ResourceNotFoundException;
import com.techproeducation.backendproject.initialwork.repository.ContactMessage.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MethodHelper {

    private final ContactMessageRepository contactMessageRepository;

    public ContactMessage isContactMessageExist(Long id) {
        return contactMessageRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Resources not found exception"));
    }

}
