package com.example.qllh.DTO.AppointmentDTO;

import com.example.qllh.Entities.Users;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentResponse {
    Long id;
    Users patientId;
    String description;
    Users doctorId;
    String patientName;
    String doctorName;
    LocalDate appointmentTime;
}
