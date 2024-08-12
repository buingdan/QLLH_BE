package com.example.qllh.DTO.MedicalRecordDTO;

import com.example.qllh.Entities.History;
import com.example.qllh.Entities.Users;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalRecordRequest {
    Users userId;
    History historyId;
}
