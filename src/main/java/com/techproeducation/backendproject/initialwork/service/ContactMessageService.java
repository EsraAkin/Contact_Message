package com.techproeducation.backendproject.initialwork.service;

import com.techproeducation.backendproject.initialwork.dto.ContactMessageDTO;
import com.techproeducation.backendproject.initialwork.entity.ContactMessage;
import com.techproeducation.backendproject.initialwork.mapper.ContactMessageMapper;
import com.techproeducation.backendproject.initialwork.payload.request.ContactMessageRequest;
import com.techproeducation.backendproject.initialwork.payload.response.ContactMessageResponse;
import com.techproeducation.backendproject.initialwork.payload.response.ResponseMessage;
import com.techproeducation.backendproject.initialwork.repository.ContactMessage.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
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



    public Page<ContactMessage> getAllContactMessagesByPage(Pageable pageable) {
        Page<ContactMessage> ContactMessagePage = contactMessageRepository.findAll(pageable);
        return ContactMessagePage;
    }

    public List<ContactMessageResponse> getContactMessageBySubject(String subject) {
        List<ContactMessage> messages = contactMessageRepository.findBySubject(subject);

        // ContactMessage nesnelerini ContactMessageResponse nesnesine dönüştürüyoruz
        return messages.stream()
                .map(contactMessageMapper::mapContactMessageToContactMessageResponse)
                .collect(Collectors.toList());
    }

    public ContactMessageResponse getContactMessageByEmail(String email) {
        ContactMessage contactMessage=contactMessageRepository.findByEmail(email);
        return contactMessageMapper.mapContactMessageToContactMessageResponse(contactMessage);
    }

    public List<ContactMessageResponse> getContactMessageByDate(LocalDateTime startDate, LocalDateTime endDate) {
        List<ContactMessage> contactMessages = contactMessageRepository.findByLocalDateTimeBetween(startDate, endDate);

        return contactMessages.stream()
                .map(contactMessageMapper::mapContactMessageToContactMessageResponse)
                .collect(Collectors.toList());
    }
}
