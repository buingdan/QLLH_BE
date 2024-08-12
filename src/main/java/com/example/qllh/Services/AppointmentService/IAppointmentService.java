package com.example.qllh.Services.AppointmentService;


import com.example.qllh.DTO.AppointmentDTO.AppointmentRequest;
import com.example.qllh.Model.ContentResponse;
import com.example.qllh.Model.ResponseApi;
import org.springframework.stereotype.Service;

@Service
public interface IAppointmentService {
    ResponseApi getAppointments();
    ResponseApi getAppointmentsById(Long id);
    ResponseApi addAppointment(AppointmentRequest appointmentRequest);

    ResponseApi updateAppointment(Long id, AppointmentRequest appointmentRequest);

    ResponseApi deleteAppointment(Long id);

    ContentResponse getPageAppointment(String textSearch, Long currentPage, Long limit, String sortData, String sortType);
}
