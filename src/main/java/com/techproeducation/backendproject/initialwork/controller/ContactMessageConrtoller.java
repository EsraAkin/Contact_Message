package com.techproeducation.backendproject.initialwork.controller;


import com.techproeducation.backendproject.initialwork.dto.ContactMessageDTO;
import com.techproeducation.backendproject.initialwork.entity.ContactMessage;
import com.techproeducation.backendproject.initialwork.payload.request.ContactMessageRequest;
import com.techproeducation.backendproject.initialwork.payload.response.ContactMessageResponse;

import com.techproeducation.backendproject.initialwork.payload.response.ResponseMessage;
import com.techproeducation.backendproject.initialwork.service.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ContactMessage")
public class ContactMessageConrtoller {


    private final ContactMessageService contactMessageService;

    //POST → Create contact messages.
    //(Date-time will be created by back-end)
    @PostMapping("/save")
    public ResponseEntity<ResponseMessage<ContactMessageResponse>> createContactMessage(
            @Valid @RequestBody ContactMessageRequest contactMessageRequest){
        return ResponseEntity.ok(contactMessageService.createContactMessage(contactMessageRequest));
    }

    //GET → Get all contact messages
    @GetMapping
    public ResponseEntity<List<ContactMessageResponse>> getAllContactMessages() {
        List<ContactMessageResponse> getAllContactMessages = contactMessageService.getAllContactMessages();
        return new ResponseEntity<>(getAllContactMessages, HttpStatus.OK);
    }

    }











    //GET → Get all contact messages by page
    //(parameters: page, size, sort, type)
    //
    //GET → Search contact messages by subject
    //(contains/LIKE parameter: {subject})
    //
    //GET → Get contact messages by email
    //(parameter: {email})
    //
    //GET → Get contact messages by creation date
    //(parameters: {startDate, endDate})
    //(Find all contact messages between two dates, example: 01.01.2022 - 01.10.2023)
    //
    //GET → Get contact messages by creation time
    //(parameters: {startTime, endTime})
    //(Find all contact messages between two times, example: 10:30 - 22:00)
    //
    //DELETE → Delete by ID (path)
    //
    //DELETE → Delete by ID (parameter: {id})
    //
    //PUT → Update a contact message
    //(Search by ID and update by body, only the properties that need to be updated will be sent)

