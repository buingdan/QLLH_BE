package com.example.qllh.Mapping;

import com.example.qllh.DTO.HistoryDTO.HistoryRequest;
import com.example.qllh.DTO.HistoryDTO.HistoryResponse;
import com.example.qllh.Entities.History;

public class HistoryMapping {
//    public static History mapRequestToEntity(HistoryRequest historyRequest) {
//        History historyEntity = new History();
//        historyEntity.setDiagnostic(historyRequest.getDiagnostic());
//        historyEntity.setInsuranceCode(historyRequest.getInsuranceCode());
//        historyEntity.setStartDate(historyRequest.getStartDate());
//        historyEntity.setEndDate(historyRequest.getEndDate());
//        historyEntity.setStatus(historyRequest.isStatus());
//        historyEntity.setMedicalRecordsId(historyRequest.getMedicalRecordsId());
//
//        return historyEntity;
//    }
//
//    public static HistoryResponse mapEntityToResponse(History historyEntity) {
//        HistoryResponse historyResponse = new HistoryResponse();
//        historyResponse.setId(historyEntity.getId());
//        historyResponse.setDiagnostic(historyEntity.getDiagnostic());
//        historyResponse.setInsuranceCode(historyEntity.getInsuranceCode());
//        historyResponse.setEndDate(historyEntity.getEndDate());
//        historyResponse.setStartDate(historyEntity.getStartDate());
//        historyResponse.setStatus(historyEntity.isStatus());
//        historyResponse.setMedicalRecordsId(historyEntity.getMedicalRecordsId());
//
//        return historyResponse;
//    }

        public static History mapRequestToEntity(HistoryRequest historyRequest) {
        History historyEntity = new History();
        historyEntity.setDiagnostic(historyRequest.getDiagnostic());
        historyEntity.setMedicalCondition(historyRequest.getMedicalCondition());
        historyEntity.setTestResults(historyRequest.getTestResults());
        historyEntity.setExaminationDate(historyRequest.getExaminationDate());
        historyEntity.setStatus(historyRequest.getStatus());
        historyEntity.setMedicalRecordsId(historyRequest.getMedicalRecordsId());

        return historyEntity;
    }

    public static HistoryResponse mapEntityToResponse(History historyEntity) {
        HistoryResponse historyResponse = new HistoryResponse();
        historyResponse.setId(historyEntity.getId());
        historyResponse.setDiagnostic(historyEntity.getDiagnostic());
        historyResponse.setDiagnostic(historyEntity.getDiagnostic());
        historyResponse.setMedicalCondition(historyEntity.getMedicalCondition());
        historyResponse.setTestResults(historyEntity.getTestResults());
        historyResponse.setExaminationDate(historyEntity.getExaminationDate());
        historyResponse.setStatus(historyEntity.getStatus());
        historyResponse.setMedicalRecordsId(historyEntity.getMedicalRecordsId());

        return historyResponse;
    }
}
