package com.techproeducation.backendproject.initialwork.repository.ContactMessage;

import com.techproeducation.backendproject.initialwork.entity.ContactMessage;
import com.techproeducation.backendproject.initialwork.payload.response.ContactMessageResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    boolean existsByEmail(String email);


    List<ContactMessage> findBySubject(String subject);

    ContactMessage findByEmail(String email);
}
