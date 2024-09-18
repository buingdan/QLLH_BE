package com.example.qllh.DTO.AppointmentDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentSearchDTO {
    String fullName;
    String insuranceCode;
    String diagnostic;
    LocalDate startDate;
    LocalDate endDate;
    String textNote;
}
