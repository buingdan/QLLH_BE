package com.example.qllh.DTO.MedicalRecordDTO;

import com.example.qllh.Entities.History;
import com.example.qllh.Entities.Users;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalRecordResponse {
    Long id;
    Users userId;
    String diagnostic;
    LocalDate startDate;
    LocalDate endDate;
    String textNote;
    String insuranceCode;
    boolean isActive;
    String progressCompleted;
}
