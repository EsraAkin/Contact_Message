package com.techproeducation.backendproject.initialwork.controller;


import com.techproeducation.backendproject.initialwork.dto.ContactMessageDTO;
import com.techproeducation.backendproject.initialwork.payload.request.ContactMessageRequest;
import com.techproeducation.backendproject.initialwork.payload.response.ContactMessageResponse;

import com.techproeducation.backendproject.initialwork.payload.response.ResponseMessage;
import com.techproeducation.backendproject.initialwork.service.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ContactMessage")
public class ContactMessageConrtoller {


    private final ContactMessageService contactMessageService;

    @PostMapping("/save")
    public ResponseEntity<ResponseMessage<ContactMessageResponse>> createContactMessage(
            @Valid @RequestBody ContactMessageRequest contactMessageRequest){
        return ResponseEntity.ok(contactMessageService.createContactMessage(contactMessageRequest));


    }

/**
 * @PostMapping("/save/{userRole}")
 *   public ResponseEntity<ResponseMessage<UserResponse>> saveUser(
 *       @RequestBody @Valid UserRequest userRequest,
 *       @PathVariable String userRole) {
 *       return ResponseEntity.ok(userService.saveUser(userRequest,userRole));
 *   }
 */


}
