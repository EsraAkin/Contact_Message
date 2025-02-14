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
        ContactMessage contactMessageToSave=contactMessageMapper.mapContactMessageRequestToContactMessage(contactMessageRequest);
        //save
        ContactMessage savedContactMessage=contactMessageRepository.save(contactMessageToSave);
        //Entity->DTO map
        return ResponseMessage.<ContactMessageResponse>builder()
                .message("Kayıt başarılı")
                .returnBody(contactMessageMapper.mapContactMessageToContactMessageResponse(savedContactMessage))
                .httpStatus(HttpStatus.OK)
                        .build();

        /**
         *  //DTO->entity mapping
         *     User userToSave = userMapper.mapUserRequestToUser(userRequest,userRole);
         *     //save operation
         *     User savedUser = userRepository.save(userToSave);
         *     //entity ->DTO mapping
         *     return ResponseMessage.<UserResponse>builder()
         *         .message(SuccessMessages.USER_CREATE)
         *         .returnBody(userMapper.mapUserToUserResponse(savedUser))
         *         .httpStatus(HttpStatus.OK)
         *         .build();
         */

    }
}
