package com.example.qllh.DTO.HistoryDTO;

import com.example.qllh.Entities.MedicalRecords;
import com.example.qllh.Entities.Users;
import com.example.qllh.Enum.ExaminationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HistoryResponse {
    Long id;
    String diagnostic;
    String medicalCondition;
    String testResults;
    LocalDate examinationDate;
    ExaminationStatus status;
    MedicalRecords medicalRecordsId;
}
