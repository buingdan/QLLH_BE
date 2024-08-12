package com.example.qllh.Services.AppointmentService;


import com.example.qllh.DTO.AppointmentDTO.AppointmentRequest;
import com.example.qllh.DTO.AppointmentDTO.AppointmentResponse;
import com.example.qllh.DTO.HistoryDTO.HistoryResponse;
import com.example.qllh.DTO.MedicalRecordDTO.MedicalRecordResponse;
import com.example.qllh.Entities.Appointments;
import com.example.qllh.Entities.History;
import com.example.qllh.Mapping.AppointmentMapping;
import com.example.qllh.Mapping.HistoryMapping;
import com.example.qllh.Mapping.MedicalRecordMapping;
import com.example.qllh.Model.ContentResponse;
import com.example.qllh.Model.ResponseApi;
import com.example.qllh.Repositories.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AppointmentService implements IAppointmentService {
    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Override
    public ResponseApi getAppointments() {
        try {
            List<Appointments> appointmentEntityList = appointmentsRepository.findAll();
            List<AppointmentResponse> appointmentResponseList = appointmentEntityList
                    .stream()
                    .map(AppointmentMapping::mapEntityToResponse)
                    .collect(Collectors.toList());
            return new ResponseApi("Lấy dữ liệu cuộc hẹn thành công!", appointmentEntityList, true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    @Override
    public ResponseApi getAppointmentsById(Long id) {
        try {
            Optional<Appointments> appointmentEntityOptional = appointmentsRepository.findById(id);
            if (appointmentEntityOptional.isEmpty()) {
                return new ResponseApi("Không tìm thấy dữ liệu lịch khám!", null, true);
            }
            var appointment = appointmentsRepository.findById(id);
            List<AppointmentResponse> appoinmentResponseList = appointment
                    .stream()
                    .map(AppointmentMapping::mapEntityToResponse)
                    .collect(Collectors.toList());
            return new ResponseApi("Xóa dữ liệu thành công!!", appoinmentResponseList , true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    @Override
    public ResponseApi addAppointment(AppointmentRequest appointmentRequest) {
        try {
            Appointments appointmentEntityList = AppointmentMapping.mapRequestToEntity(appointmentRequest);
            appointmentsRepository.save(appointmentEntityList);
            return new ResponseApi("Thêm cuộc hẹn thành công!", null, true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    @Override
    public ResponseApi updateAppointment(Long id, AppointmentRequest appointmentRequest) {
        try {
            Optional<Appointments> appointmentEntityOptional = appointmentsRepository.findById(id);
            if (appointmentEntityOptional.isEmpty()) {
                return new ResponseApi("Không tìm thấy dữ liệu cuộc hẹn!", null, true);
            }
            Appointments appointmentEntity = appointmentEntityOptional.get();
            appointmentEntity.setPatientId(appointmentRequest.getPatientId());
            appointmentEntity.setDescription(appointmentRequest.getDescription());
            appointmentEntity.setDoctorId(appointmentRequest.getDoctorId());


            appointmentsRepository.save(appointmentEntity);
            return new ResponseApi("Cập nhật dữ liệu thành công!", null, true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    @Override
    public ResponseApi deleteAppointment(Long id) {
        try {
            Optional<Appointments> appointmentEntityOptional = appointmentsRepository.findById(id);
            if (appointmentEntityOptional.isEmpty()) {
                return new ResponseApi("Không tìm thấy dữ liệu cuộc hẹn", null, true);
            }
            appointmentsRepository.deleteById(id);
            return new ResponseApi("Xóa dữ liệu thành công!!", null, true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    @Override
    public ContentResponse getPageAppointment(String textSearch, Long currentPage, Long limit, String sortData, String sortType) {
        ContentResponse contentResponse = new ContentResponse();
        currentPage -= 1;
        Pageable pageable = PageRequest.of(currentPage.intValue(), limit.intValue(), Sort.by(sortOrder(sortData, sortType)));
        var list = appointmentsRepository.searchAppointment(textSearch, pageable);
        List<AppointmentResponse> appointmentResponseList = list
                .stream()
                .map(AppointmentMapping::mapEntityToResponse)
                .collect(Collectors.toList());

        Integer totalAppointment = Math.toIntExact(list.getTotalElements());
        Integer totalPageApointment = Math.toIntExact(list.getTotalPages());
        if (currentPage.intValue() > totalPageApointment) {
            currentPage = totalPageApointment.longValue();
            pageable = PageRequest.of(currentPage.intValue(), limit.intValue(), Sort.by(sortOrder(sortData, sortType)));
            list = appointmentsRepository.searchAppointment(textSearch, pageable);
            appointmentResponseList = list
                    .stream()
                    .map(AppointmentMapping::mapEntityToResponse)
                    .collect(Collectors.toList());
            totalAppointment = Math.toIntExact(list.getTotalElements());
        }
        contentResponse.setList(appointmentResponseList);
        contentResponse.setCurrentPage(currentPage.intValue() + 1);
        contentResponse.setTotalRecord(totalAppointment);
        return contentResponse;
    }

    public List<Sort.Order> sortOrder(String sort, String sortDirection) {
        System.out.println(sortDirection);
        List<Sort.Order> sorts = new ArrayList<>();
        Sort.Direction direction;
        if (sortDirection != null) {
            direction = Sort.Direction.fromString(sortDirection);
        } else {
            direction = Sort.Direction.DESC;
        }
        sorts.add(new Sort.Order(direction, sort));
        return sorts;
    }
}
