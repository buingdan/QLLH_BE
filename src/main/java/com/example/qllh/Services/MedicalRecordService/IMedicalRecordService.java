package com.example.qllh.Services.MedicalRecordService;


import com.example.qllh.DTO.MedicalRecordDTO.MedicalRecordRequest;
import com.example.qllh.Model.ContentResponse;
import com.example.qllh.Model.ResponseApi;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface IMedicalRecordService {
    ResponseApi getMedicalRecords();
    ResponseApi getMedicalRecordsById(Long id);

    ResponseApi addMedicalRecord(MedicalRecordRequest medicalRecordRequest);

    ResponseApi updateMedicalRecord(Long id, MedicalRecordRequest medicalRecordRequest);

    ResponseApi deleteMedicalRecord(Long id);

    ContentResponse getPageMedicalRecords(String patientSearch, String insuranceSearch, LocalDate startDate, LocalDate endDate, Long currentPage, Long limit, String sortData, String sortType);
}
