package com.example.qllh.Services.MedicalRecordService;


import com.example.qllh.DTO.MedicalRecordDTO.MedicalRecordRequest;
import com.example.qllh.DTO.MedicalRecordDTO.MedicalRecordResponse;
import com.example.qllh.Entities.MedicalRecords;
import com.example.qllh.Mapping.MedicalRecordMapping;
import com.example.qllh.Model.ContentResponse;
import com.example.qllh.Model.ResponseApi;
import com.example.qllh.Repositories.MedicalRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MedicalRecordService implements IMedicalRecordService {
    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    @Override
    public ResponseApi getMedicalRecords() {
        try {
            List<MedicalRecords> medicalRecordEntityList = medicalRecordsRepository.findAll();
            List<MedicalRecordResponse> medicalRecordResponseList = medicalRecordEntityList
                    .stream()
                    .map(MedicalRecordMapping::mapEntityToResponse)
                    .collect(Collectors.toList());
            return new ResponseApi("Lấy dữ liệu hồ sơ bệnh án thành công!", medicalRecordResponseList, true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    @Override
    public ResponseApi getMedicalRecordsById(Long id) {
        try {
            Optional<MedicalRecords> medicalRecordsEntityOptional = medicalRecordsRepository.findById(id);
            if (medicalRecordsEntityOptional.isEmpty()) {
                return new ResponseApi("Không tìm thấy dữ liệu hồ sơ bệnh lí!", null, true);
            }
            var medicalRecord = medicalRecordsRepository.findById(id);
            List<MedicalRecordResponse> medicalRecordResponseList = medicalRecord
                    .stream()
                    .map(MedicalRecordMapping::mapEntityToResponse)
                    .collect(Collectors.toList());
            return new ResponseApi("Xóa dữ liệu thành công!!", medicalRecordResponseList , true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    @Override
    public ResponseApi addMedicalRecord(MedicalRecordRequest medicalRecordRequest) {
        try {
            MedicalRecords medicalRecordEntityList = MedicalRecordMapping.mapRequestToEntity(medicalRecordRequest);
            medicalRecordEntityList.setActive(true);
            medicalRecordEntityList.setProgressCompleted("");
            medicalRecordsRepository.save(medicalRecordEntityList);
            return new ResponseApi("Thêm hồ sơ bệnh án thành công!", null, true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    @Override
    public ResponseApi updateMedicalRecord(Long id, MedicalRecordRequest medicalRecordRequest) {
        try {
            Optional<MedicalRecords> medicalRecordEntityOptional = medicalRecordsRepository.findById(id);
            if (medicalRecordEntityOptional.isEmpty()) {
                return new ResponseApi("Không tìm thấy dữ liệu hồ sơ bệnh án!", null, true);
            }
            MedicalRecords medicalRecordEntity = medicalRecordEntityOptional.get();
            medicalRecordEntity.setUserId(medicalRecordRequest.getUserId());
            medicalRecordEntity.setDiagnostic(medicalRecordRequest.getDiagnostic());
            medicalRecordEntity.setEndDate(medicalRecordRequest.getEndDate());
            medicalRecordEntity.setStartDate(medicalRecordRequest.getStartDate());
            medicalRecordEntity.setInsuranceCode(medicalRecordRequest.getInsuranceCode());
            medicalRecordEntity.setProgressCompleted(medicalRecordEntity.getProgressCompleted());
            medicalRecordEntity.setTextNote(medicalRecordRequest.getTextNote());

            medicalRecordsRepository.save(medicalRecordEntity);
            return new ResponseApi("Cập nhật dữ liệu hồ sơ bệnh án thành công!", null, true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    @Override
    public ResponseApi deleteMedicalRecord(Long id) {
        try {
            Optional<MedicalRecords> medicalRecordEntityOptional = medicalRecordsRepository.findById(id);
            if (medicalRecordEntityOptional.isEmpty()) {
                return new ResponseApi("Không tìm thấy dữ liệu hồ sơ bệnh án", null, true);
            }
            medicalRecordsRepository.softDelete(id);
            return new ResponseApi("Xóa dữ liệu hồ sơ bệnh án!!", null, true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    @Override
    public ContentResponse getPageMedicalRecords(String patientSearch, String insuranceSearch, LocalDate startDate,LocalDate endDate, Long currentPage, Long limit, String sortData, String sortType) {
        ContentResponse contentResponse = new ContentResponse();
        currentPage -= 1;
        Pageable pageable = PageRequest.of(currentPage.intValue(), limit.intValue(), Sort.by(sortOrder(sortData, sortType)));
        var list = medicalRecordsRepository.searchMedicalRecords(patientSearch,insuranceSearch, startDate, endDate, pageable);
        List<MedicalRecordResponse> medicalRecordsResponseList = list
                .stream()
                .map(MedicalRecordMapping::mapEntityToResponse)
                .collect(Collectors.toList());

        Integer totalMedicalRecord = Math.toIntExact(list.getTotalElements());
        Integer totalPageMedicalRecord = Math.toIntExact(list.getTotalPages());
        if (currentPage.intValue() > totalPageMedicalRecord) {
            currentPage = totalPageMedicalRecord.longValue();
            pageable = PageRequest.of(currentPage.intValue(), limit.intValue(), Sort.by(sortOrder(sortData, sortType)));
            list = medicalRecordsRepository.searchMedicalRecords(patientSearch,insuranceSearch, startDate, endDate, pageable);;
            medicalRecordsResponseList = list
                    .stream()
                    .map(MedicalRecordMapping::mapEntityToResponse)
                    .collect(Collectors.toList());
            totalMedicalRecord = Math.toIntExact(list.getTotalElements());
        }
        contentResponse.setList(medicalRecordsResponseList);
        contentResponse.setCurrentPage(currentPage.intValue() + 1);
        contentResponse.setTotalRecord(totalMedicalRecord);
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
