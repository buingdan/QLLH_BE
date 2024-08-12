package com.example.qllh.DTO.HistoryDTO;

import com.example.qllh.Entities.Users;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HistoryRequest {
    String insuranceCode;
    String diagnostic;
    LocalDateTime startDate;
    LocalDateTime endDate;
}
