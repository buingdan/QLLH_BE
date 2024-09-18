package com.example.qllh.DTO.UserDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;
    String fullName;
    Integer age;
    boolean gender;
    String phoneNumber;
    String address;
    String email;
    String role;
    boolean isActive;
    String textNote;
}
