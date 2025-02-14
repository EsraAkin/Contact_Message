package com.techproeducation.backendproject.initialwork.controller;


import com.techproeducation.backendproject.initialwork.dto.ContactMessageDTO;
import com.techproeducation.backendproject.initialwork.entity.ContactMessage;
import com.techproeducation.backendproject.initialwork.payload.request.ContactMessageRequest;
import com.techproeducation.backendproject.initialwork.payload.response.ContactMessageResponse;

import com.techproeducation.backendproject.initialwork.payload.response.ResponseMessage;
import com.techproeducation.backendproject.initialwork.service.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
            @Valid @RequestBody ContactMessageRequest contactMessageRequest) {
        return ResponseEntity.ok(contactMessageService.createContactMessage(contactMessageRequest));
    }

    //GET → Get all contact messages
    @GetMapping
    public ResponseEntity<List<ContactMessageResponse>> getAllContactMessages() {
        List<ContactMessageResponse> getAllContactMessages = contactMessageService.getAllContactMessages();
        return new ResponseEntity<>(getAllContactMessages, HttpStatus.OK);
    }

    //GET → Get all contact messages by page
    //(parameters: page, size, sort, type)


    @GetMapping("/page")
    public ResponseEntity<Page<ContactMessage>> getAllStudents(@RequestParam("page") int pageNo, //kaçıncı sayfayı görmek istiyorum.
                                                               @RequestParam("size") int size,   //her sayfada kaç kayıt olacak
                                                               @RequestParam("sort") String properties, //hangi özelliğer göre sıralama yapılacak
                                                               @RequestParam("direction") Sort.Direction direction) { //sıralamanın yönü için sabit değişken

        Pageable pageable = PageRequest.of(pageNo, size, Sort.by(direction, properties));
        Page<ContactMessage> studentPage = contactMessageService.getAllContactMessagesByPage(pageable);
        return new ResponseEntity<>(studentPage, HttpStatus.OK); //200
    }


    //GET → Search contact messages by subject
    //(contains/LIKE parameter: {subject})

    @GetMapping("/get/{subject}")
    public ResponseEntity<List<ContactMessageResponse>> getContactMessageBySubject(@PathVariable String subject) {
        List<ContactMessageResponse> messages = contactMessageService.getContactMessageBySubject(subject);
        return ResponseEntity.ok(messages);
    }


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

}