package com.example.qllh.DTO.UserDTO;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    String fullName;
    Integer age;
    boolean gender;
    String phoneNumber;
    String address;
    String email;
    String role;
    boolean isActive;
}
