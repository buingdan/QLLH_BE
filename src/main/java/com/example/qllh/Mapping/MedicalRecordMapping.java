package com.example.qllh.Mapping;

import com.example.qllh.DTO.MedicalRecordDTO.MedicalRecordRequest;
import com.example.qllh.DTO.MedicalRecordDTO.MedicalRecordResponse;
import com.example.qllh.DTO.UserDTO.UserRequest;
import com.example.qllh.DTO.UserDTO.UserResponse;
import com.example.qllh.Entities.MedicalRecords;
import com.example.qllh.Entities.Users;

public class MedicalRecordMapping {
    public static MedicalRecords mapRequestToEntity(MedicalRecordRequest medicalRecordRequest) {
        MedicalRecords medicalRecordEntity = new MedicalRecords();
        medicalRecordEntity.setUserId(medicalRecordRequest.getUserId());
        medicalRecordEntity.setHistoryId(medicalRecordRequest.getHistoryId());

        return medicalRecordEntity;
    }

    public static MedicalRecordResponse mapEntityToResponse(MedicalRecords medicalRecordEntity) {
        MedicalRecordResponse medicalRecordResponse = new MedicalRecordResponse();
        medicalRecordResponse.setId(medicalRecordEntity.getId());
        medicalRecordResponse.setUserId(medicalRecordEntity.getUserId());
        medicalRecordResponse.setHistoryId(medicalRecordEntity.getHistoryId());

        return medicalRecordResponse;
    }
}
