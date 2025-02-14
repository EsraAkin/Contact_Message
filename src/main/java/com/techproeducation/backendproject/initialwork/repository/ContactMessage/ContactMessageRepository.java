package com.techproeducation.backendproject.initialwork.repository.ContactMessage;

import com.techproeducation.backendproject.initialwork.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}
