package com.techproeducation.backendproject.initialwork.service;

import com.techproeducation.backendproject.initialwork.entity.ContactMessage;
import com.techproeducation.backendproject.initialwork.mapper.ContactMessageMapper;
import com.techproeducation.backendproject.initialwork.payload.request.ContactMessageRequest;
import com.techproeducation.backendproject.initialwork.payload.response.ContactMessageResponse;
import com.techproeducation.backendproject.initialwork.payload.response.ResponseMessage;
import com.techproeducation.backendproject.initialwork.repository.ContactMessage.ContactMessageRepository;
import com.techproeducation.backendproject.initialwork.service.helper.ContactMessageUpdateHelper;
import com.techproeducation.backendproject.initialwork.service.helper.MethodHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private final UniqueValidator uniqueValidator;
    private final ContactMessageMapper contactMessageMapper;
    private final ContactMessageRepository contactMessageRepository;
    private final MethodHelper methodHelper;
    private final ContactMessageUpdateHelper updateHelper;

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
        ContactMessage contactMessage = methodHelper.isContactMessageExistEmail(email);
        return contactMessageMapper.mapContactMessageToContactMessageResponse(contactMessage);

    }

    public List<ContactMessageResponse> getContactMessageByDate(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay(); // 00:00:00
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59); // 23:59:59

        List<ContactMessage> messages = contactMessageRepository.findByLocalDateTimeBetween(startDateTime, endDateTime);

        return messages.stream()
                .map(contactMessageMapper::mapContactMessageToContactMessageResponse)
                .collect(Collectors.toList());
    }

    public List<ContactMessageResponse> getContactMessageByTime(LocalTime startTime, LocalTime endTime) {
            List<ContactMessage> messages = contactMessageRepository.findAll();

            // `LocalTime` bazında filtreleme
            List<ContactMessage> filteredMessages = messages.stream()
                    .filter(msg -> {
                        LocalTime messageTime = msg.getLocalDateTime().toLocalTime();
                        return messageTime.isAfter(startTime) && messageTime.isBefore(endTime);
                    })
                    .collect(Collectors.toList());

            return filteredMessages.stream()
                    .map(contactMessageMapper::mapContactMessageToContactMessageResponse)
                    .collect(Collectors.toList());
        }

    public ContactMessageResponse deleteContactMessageById(Long id) {

        ContactMessage contactMessage =methodHelper.isContactMessageExist(id);

        contactMessageRepository.deleteById(id);

        // Silinen mesajın bilgilerini response olarak döndür
        return contactMessageMapper.mapContactMessageToContactMessageResponse(contactMessage);
    }


    public ResponseMessage<ContactMessageResponse> updateContactMessageById(
            ContactMessageRequest contactMessageRequest, Long messageId) {


        // Mevcut mesajı veritabanından getir
        ContactMessage contactMessageDb = methodHelper.isContactMessageExist(messageId);

        // Güncelleme izinleri kontrol et
        methodHelper.checkBuildIn(contactMessageDb);
        uniqueValidator.checkUniqueProperty(contactMessageDb, contactMessageRequest);

        // **Sadece değiştirilen alanları güncelle**
        updateHelper.updateContactMessageFields(contactMessageDb, contactMessageRequest);

        // Güncellenmiş mesajı kaydet
        ContactMessage savedMessage = contactMessageRepository.save(contactMessageDb);

        // Response döndür
        return ResponseMessage.<ContactMessageResponse>builder()
                .message("Mesaj başarıyla güncellendi")
                .returnBody(contactMessageMapper.mapContactMessageToContactMessageResponse(savedMessage))
                .httpStatus(HttpStatus.OK)
                .build();
    }


}


