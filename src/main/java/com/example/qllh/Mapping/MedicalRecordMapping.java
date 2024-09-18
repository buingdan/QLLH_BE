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
        medicalRecordEntity.setInsuranceCode(medicalRecordRequest.getInsuranceCode());
        medicalRecordEntity.setDiagnostic(medicalRecordRequest.getDiagnostic());
        medicalRecordEntity.setStartDate(medicalRecordRequest.getStartDate());
        medicalRecordEntity.setEndDate(medicalRecordRequest.getEndDate());
        medicalRecordEntity.setTextNote(medicalRecordRequest.getTextNote());
        medicalRecordEntity.setProgressCompleted(medicalRecordRequest.getProgressCompleted());

        return medicalRecordEntity;
    }

    public static MedicalRecordResponse mapEntityToResponse(MedicalRecords medicalRecordEntity) {
        MedicalRecordResponse medicalRecordResponse = new MedicalRecordResponse();
        medicalRecordResponse.setId(medicalRecordEntity.getId());
        medicalRecordResponse.setUserId(medicalRecordEntity.getUserId());
        medicalRecordResponse.setInsuranceCode(medicalRecordEntity.getInsuranceCode());
        medicalRecordResponse.setDiagnostic(medicalRecordEntity.getDiagnostic());
        medicalRecordResponse.setStartDate(medicalRecordEntity.getStartDate());
        medicalRecordResponse.setEndDate(medicalRecordEntity.getEndDate());
        medicalRecordResponse.setTextNote(medicalRecordEntity.getTextNote());
        medicalRecordResponse.setProgressCompleted(medicalRecordEntity.getProgressCompleted());

        return medicalRecordResponse;
    }
}
