package com.example.qllh.DTO.MedicalRecordDTO;

import com.example.qllh.Entities.History;
import com.example.qllh.Entities.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalRecordRequest {
    Users userId;
    String diagnostic;
    LocalDate startDate;
    LocalDate endDate;
    String textNote;
    String insuranceCode;
    boolean isActive;
    String progressCompleted;
}
