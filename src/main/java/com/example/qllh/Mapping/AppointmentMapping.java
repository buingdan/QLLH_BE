package com.example.qllh.Mapping;

import com.example.qllh.DTO.AppointmentDTO.AppointmentRequest;
import com.example.qllh.DTO.AppointmentDTO.AppointmentResponse;
import com.example.qllh.DTO.MedicalRecordDTO.MedicalRecordRequest;
import com.example.qllh.DTO.MedicalRecordDTO.MedicalRecordResponse;
import com.example.qllh.Entities.Appointments;
import com.example.qllh.Entities.MedicalRecords;

public class AppointmentMapping {
    public static Appointments mapRequestToEntity(AppointmentRequest appointmentRequest) {
        Appointments appointmentEntity = new Appointments();
        appointmentEntity.setPatientId(appointmentRequest.getPatientId());
        appointmentEntity.setDescription(appointmentRequest.getDescription());
        appointmentEntity.setDoctorId(appointmentRequest.getDoctorId());

        return appointmentEntity;
    }

    public static AppointmentResponse mapEntityToResponse(Appointments appointmentEntity) {
        AppointmentResponse appointmentResponse = new AppointmentResponse();
        appointmentResponse.setId(appointmentEntity.getId());
        appointmentResponse.setPatientId(appointmentEntity.getPatientId());
        appointmentResponse.setDescription(appointmentEntity.getDescription());
        appointmentResponse.setDoctorId(appointmentEntity.getDoctorId());

        return appointmentResponse;
    }
}
