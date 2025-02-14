package com.techproeducation.backendproject.initialwork.service;

import com.techproeducation.backendproject.initialwork.dto.ContactMessageDTO;
import com.techproeducation.backendproject.initialwork.entity.ContactMessage;
import com.techproeducation.backendproject.initialwork.mapper.ContactMessageMapper;
import com.techproeducation.backendproject.initialwork.payload.request.ContactMessageRequest;
import com.techproeducation.backendproject.initialwork.payload.response.ContactMessageResponse;
import com.techproeducation.backendproject.initialwork.payload.response.ResponseMessage;
import com.techproeducation.backendproject.initialwork.repository.ContactMessage.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private final UniqueValidator uniqueValidator;
    private final ContactMessageMapper contactMessageMapper;
    private final ContactMessageRepository contactMessageRepository;

    public ResponseMessage<ContactMessageResponse> createContactMessage(ContactMessageRequest contactMessageRequest) {

        //uniq control
        uniqueValidator.checkDuplication(contactMessageRequest.getEmail());
        //DTO->Entity
        ContactMessage contactMessageToSave = contactMessageMapper.mapContactMessageRequestToContactMessage(contactMessageRequest);
        //save
        ContactMessage savedContactMessage = contactMessageRepository.save(contactMessageToSave);
        //Entity->DTO map
        return ResponseMessage.<ContactMessageResponse>builder()
                .message("Kayıt başarılı")
                .returnBody(contactMessageMapper.mapContactMessageToContactMessageResponse(savedContactMessage))
                .httpStatus(HttpStatus.OK)
                .build();


    }


    public List<ContactMessageResponse> getAllContactMessages() {
        List<ContactMessage> getAllContactMessages = contactMessageRepository.findAll();

        // ContactMessage nesnelerini ContactMessageResponse nesnelerine çevir
        return getAllContactMessages.stream()
                .map(contactMessageMapper::mapContactMessageToContactMessageResponse)
                .collect(Collectors.toList());
    }
}
