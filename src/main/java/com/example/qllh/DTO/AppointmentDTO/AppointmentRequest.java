package com.example.qllh.DTO.AppointmentDTO;

import com.example.qllh.Entities.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentRequest {
    Users patientId;
    String description;
    Users doctorId;
    String patientName;
    String doctorName;
    LocalDate appointmentTime;
}
