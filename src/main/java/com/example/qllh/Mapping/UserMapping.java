package com.example.qllh.Mapping;

import com.example.qllh.DTO.UserDTO.UserRequest;
import com.example.qllh.DTO.UserDTO.UserResponse;
import com.example.qllh.Entities.Users;
import lombok.Data;

@Data
public class UserMapping {
    public static Users mapRequestToEntity(UserRequest userRequest) {
        Users userEntity = new Users();
        userEntity.setFullName(userRequest.getFullName());
        userEntity.setAge(userRequest.getAge());
        userEntity.setGender(userRequest.isGender());
        userEntity.setPhoneNumber(userRequest.getPhoneNumber());
        userEntity.setAddress(userRequest.getAddress());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setRole(userRequest.getRole());
        return userEntity;
    }

    public static UserResponse mapEntityToResponse(Users userEntity) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userEntity.getId());
        userResponse.setFullName(userEntity.getFullName());
        userResponse.setAge(userEntity.getAge());
        userResponse.setGender(userEntity.isGender());
        userResponse.setPhoneNumber(userEntity.getPhoneNumber());
        userResponse.setAddress(userEntity.getAddress());
        userResponse.setEmail(userEntity.getEmail());
        userResponse.setRole(userEntity.getRole());
        return userResponse;
    }
}

