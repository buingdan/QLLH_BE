package com.example.qllh.DTO.AppointmentDTO;

import com.example.qllh.Entities.Users;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
}
