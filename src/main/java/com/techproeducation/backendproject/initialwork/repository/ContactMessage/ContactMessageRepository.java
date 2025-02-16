package com.techproeducation.backendproject.initialwork.repository.ContactMessage;

import com.techproeducation.backendproject.initialwork.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    boolean existsByEmail(String email);

    List<ContactMessage> findBySubject(String subject);

    Optional<ContactMessage> findByEmail(String email);

    List<ContactMessage> findByLocalDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    // With SQL Query filter
    @Query("SELECT c FROM ContactMessage c WHERE c.localDateTime BETWEEN :startDateTime AND :endDateTime")
    List<ContactMessage> findMessagesBetweenDates(
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime);


}
