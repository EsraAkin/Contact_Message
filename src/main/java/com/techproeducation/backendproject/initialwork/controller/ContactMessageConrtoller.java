package com.techproeducation.backendproject.initialwork.controller;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

import java.time.LocalTime;
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

    @GetMapping("/subject/{subject}")
    public ResponseEntity<List<ContactMessageResponse>> getContactMessageBySubject(@PathVariable String subject) {
        List<ContactMessageResponse> messages = contactMessageService.getContactMessageBySubject(subject);
        return ResponseEntity.ok(messages);
    }

    //GET → Get contact messages by email
    //(parameter: {email})

    @GetMapping("/email/{email}")
    public ResponseEntity<ContactMessageResponse> getContactMessages(@PathVariable String email){
        return ResponseEntity.ok(contactMessageService.getContactMessageByEmail(email));
    }

    //GET → Get contact messages by creation date
    //(parameters: {startDate, endDate})
    //(Find all contact messages between two dates, example: 01.01.2022 - 01.10.2023)

    @GetMapping("/date")
    public ResponseEntity<List<ContactMessageResponse>> getContactMessagesBetweenDates(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        List<ContactMessageResponse> messages = contactMessageService.getContactMessageByDate(startDate, endDate);
        return ResponseEntity.ok(messages);
    }


    //GET → Get contact messages by creation time
    //(parameters: {startTime, endTime})
    //(Find all contact messages between two times, example: 10:30 - 22:00)

    @GetMapping("/time")
    public ResponseEntity<List<ContactMessageResponse>> getContactMessagesBetweenTimes(
            @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime startTime,
            @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime endTime) {

        List<ContactMessageResponse> messages = contactMessageService.getContactMessageByTime(startTime, endTime);

        return ResponseEntity.ok(messages);
    }

    //DELETE → Delete by ID (path)
    @DeleteMapping("deleteContactMessageById/{id}")
    public ResponseEntity<ContactMessageResponse>deletedByIdPath(@PathVariable Long id){
        return ResponseEntity.ok( contactMessageService.deleteContactMessageById(id));
    }


    //DELETE → Delete by ID (parameter: {id})
    @DeleteMapping("delete")
    public ResponseEntity<ContactMessageResponse> deletedByIdParam(@RequestParam Long id) {
        return ResponseEntity.ok(contactMessageService.deleteContactMessageById(id));
    }

    //PUT → Update a contact message
    //(Search by ID and update by body, only the properties that need to be updated will be sent)

    @PutMapping("/update/{messageId}")
    public ResponseMessage<ContactMessageResponse> updateMessageById(
            @RequestBody ContactMessageRequest contactMessageRequest,
            @PathVariable Long messageId) {
        return contactMessageService.updateContactMessageById(contactMessageRequest, messageId);
    }

}