package com.example.qllh.Services.HistoryService;


import com.example.qllh.DTO.HistoryDTO.HistoryRequest;
import com.example.qllh.DTO.HistoryDTO.HistoryResponse;
import com.example.qllh.Entities.History;
import com.example.qllh.Entities.MedicalRecords;
import com.example.qllh.Enum.ExaminationStatus;
import com.example.qllh.Mapping.HistoryMapping;
import com.example.qllh.Model.ContentResponse;
import com.example.qllh.Model.ResponseApi;
import com.example.qllh.Repositories.HistoryRepository;
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
public class HistoryService implements IHistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    @Override
    public ResponseApi getHistory() {
        try {
            List<History> historyEntityList = historyRepository.findAll();
            List<HistoryResponse> HistoryResponseList = historyEntityList
                    .stream()
                    .map(HistoryMapping::mapEntityToResponse)
                    .collect(Collectors.toList());
            return new ResponseApi("Lấy dữ liệu lịch sử khám thành công!", historyEntityList, true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }



    @Override
    public ResponseApi getHistoryById(Long id) {
        try {
            Optional<History> historyEntityOptional = historyRepository.findById(id);
            if (historyEntityOptional.isEmpty()) {
                return new ResponseApi("Không tìm thấy dữ liệu lịch sử khám!", null, true);
            }
            var history = historyRepository.findById(id);
            List<HistoryResponse> historyResponseList = history
                    .stream()
                    .map(HistoryMapping::mapEntityToResponse)
                    .collect(Collectors.toList());
            return new ResponseApi("Xóa dữ liệu thành công!!", historyResponseList , true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    @Override
    public ResponseApi addHistory(HistoryRequest historyRequest) {
        try {
            History historyEntityList = HistoryMapping.mapRequestToEntity(historyRequest);
            historyEntityList.setStatus(ExaminationStatus.CHUA_KHAM);
            historyRepository.save(historyEntityList);
            int done = historyRepository.countDone();
            int sum = historyRepository.countSum();
            int cancle = historyRepository.countCancel();
            Optional<MedicalRecords> medicalRecordEntityOptional = medicalRecordsRepository.findById(historyRequest.getMedicalRecordsId().getId());
            if (medicalRecordEntityOptional.isEmpty()) {
                return new ResponseApi("Không tìm thấy dữ liệu hồ sơ bệnh án!", null, true);
            }
            MedicalRecords medicalRecordEntity = medicalRecordEntityOptional.get();
            medicalRecordEntity.setUserId(medicalRecordEntity.getUserId());
            medicalRecordEntity.setDiagnostic(medicalRecordEntity.getDiagnostic());
            medicalRecordEntity.setEndDate(medicalRecordEntity.getEndDate());
            medicalRecordEntity.setStartDate(medicalRecordEntity.getStartDate());
            medicalRecordEntity.setInsuranceCode(medicalRecordEntity.getInsuranceCode());
            medicalRecordEntity.setTextNote(medicalRecordEntity.getTextNote());
            medicalRecordEntity.setProgressCompleted(calcProgressCompleted( done,  sum,  cancle));

            medicalRecordsRepository.save(medicalRecordEntity);

            return new ResponseApi("Thêm lịch sử khám thành công!", null, true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    @Override
    public ResponseApi updateHistory(Long id, HistoryRequest historyRequest) {
        try {
            Optional<History> historyEntityOptional = historyRepository.findById(id);
            if (historyEntityOptional.isEmpty()) {
                return new ResponseApi("Không tìm thấy dữ liệu lịch sử khám!", null, true);
            }
            History historyEntity = historyEntityOptional.get();
            historyEntity.setDiagnostic(historyRequest.getDiagnostic());
            historyEntity.setMedicalCondition(historyRequest.getMedicalCondition());
            historyEntity.setTestResults(historyRequest.getTestResults());
            historyEntity.setExaminationDate(historyRequest.getExaminationDate());
            historyEntity.setMedicalRecordsId(historyRequest.getMedicalRecordsId());
            historyEntity.setStatus(historyRequest.getStatus());

            historyRepository.save(historyEntity);

            int done = historyRepository.countDone();
            int sum = historyRepository.countSum();
            int cancle = historyRepository.countCancel();
            Optional<MedicalRecords> medicalRecordEntityOptional = medicalRecordsRepository.findById(historyRequest.getMedicalRecordsId().getId());
            if (medicalRecordEntityOptional.isEmpty()) {
                return new ResponseApi("Không tìm thấy dữ liệu hồ sơ bệnh án!", null, true);
            }
            MedicalRecords medicalRecordEntity = medicalRecordEntityOptional.get();
            medicalRecordEntity.setUserId(medicalRecordEntity.getUserId());
            medicalRecordEntity.setDiagnostic(medicalRecordEntity.getDiagnostic());
            medicalRecordEntity.setEndDate(medicalRecordEntity.getEndDate());
            medicalRecordEntity.setStartDate(medicalRecordEntity.getStartDate());
            medicalRecordEntity.setInsuranceCode(medicalRecordEntity.getInsuranceCode());
            medicalRecordEntity.setTextNote(medicalRecordEntity.getTextNote());
            medicalRecordEntity.setProgressCompleted(calcProgressCompleted( done,  sum,  cancle));

            medicalRecordsRepository.save(medicalRecordEntity);
            return new ResponseApi("Cập nhật dữ liệu lịch khám thành công!", null, true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    @Override
    public ResponseApi deleteHistory(Long id) {
        try {
            Optional<History> historyEntityOptional = historyRepository.findById(id);
            if (historyEntityOptional.isEmpty()) {
                return new ResponseApi("Không tìm thấy dữ liệu lịch khám", null, true);
            }
            historyRepository.deleteById(id);
            int done = historyRepository.countDone();
            int sum = historyRepository.countSum();
            int cancle = historyRepository.countCancel();
            Optional<MedicalRecords> medicalRecordEntityOptional = medicalRecordsRepository.findById(historyEntityOptional.get().getMedicalRecordsId().getId());
            if (medicalRecordEntityOptional.isEmpty()) {
                return new ResponseApi("Không tìm thấy dữ liệu hồ sơ bệnh án!", null, true);
            }
            MedicalRecords medicalRecordEntity = medicalRecordEntityOptional.get();
            medicalRecordEntity.setUserId(medicalRecordEntity.getUserId());
            medicalRecordEntity.setDiagnostic(medicalRecordEntity.getDiagnostic());
            medicalRecordEntity.setEndDate(medicalRecordEntity.getEndDate());
            medicalRecordEntity.setStartDate(medicalRecordEntity.getStartDate());
            medicalRecordEntity.setInsuranceCode(medicalRecordEntity.getInsuranceCode());
            medicalRecordEntity.setTextNote(medicalRecordEntity.getTextNote());
            medicalRecordEntity.setProgressCompleted(calcProgressCompleted( done,  sum,  cancle));

            medicalRecordsRepository.save(medicalRecordEntity);
            return new ResponseApi("Xóa dữ liệu thành công!!", null, true);
        } catch (Exception e) {
            return new ResponseApi(e.getMessage(), null, false);
        }
    }

    @Override
    public ContentResponse getPageHistory(String textSearch, Long currentPage, Long limit, String sortData, String sortType) {
        ContentResponse contentResponse = new ContentResponse();
        currentPage -= 1;
        Pageable pageable = PageRequest.of(currentPage.intValue(), limit.intValue(), Sort.by(sortOrder(sortData, sortType)));
        var list = historyRepository.searchHistory(textSearch, pageable);
        List<HistoryResponse> medicalRecordsResponseList = list
                .stream()
                .map(HistoryMapping::mapEntityToResponse)
                .collect(Collectors.toList());

        Integer totalHistory = Math.toIntExact(list.getTotalElements());
        Integer totalPageHistory = Math.toIntExact(list.getTotalPages());
        if (currentPage.intValue() > totalPageHistory) {
            currentPage = totalPageHistory.longValue();
            pageable = PageRequest.of(currentPage.intValue(), limit.intValue(), Sort.by(sortOrder(sortData, sortType)));
            list = historyRepository.searchHistory(textSearch, pageable);
            medicalRecordsResponseList = list
                    .stream()
                    .map(HistoryMapping::mapEntityToResponse)
                    .collect(Collectors.toList());
            totalHistory = Math.toIntExact(list.getTotalElements());
        }
        contentResponse.setList(medicalRecordsResponseList);
        contentResponse.setCurrentPage(currentPage.intValue() + 1);
        contentResponse.setTotalRecord(totalHistory);
        return contentResponse;
    }

    @Override
    public ContentResponse getPageHistoryNew(LocalDate startDate, LocalDate endDate, Long currentPage, Long limit, String sortData, String sortType) {
        ContentResponse contentResponse = new ContentResponse();
        currentPage -= 1;
        Pageable pageable = PageRequest.of(currentPage.intValue(), limit.intValue(), Sort.by(sortOrder(sortData, sortType)));
        var list = historyRepository.searchHistoryNew(startDate, endDate, pageable);
        List<HistoryResponse> historysResponseList = list
                .stream()
                .map(HistoryMapping::mapEntityToResponse)
                .collect(Collectors.toList());

        Integer totalHistory = Math.toIntExact(list.getTotalElements());
        Integer totalPageHistory = Math.toIntExact(list.getTotalPages());
        if (currentPage.intValue() > totalPageHistory) {
            currentPage = totalPageHistory.longValue();
            pageable = PageRequest.of(currentPage.intValue(), limit.intValue(), Sort.by(sortOrder(sortData, sortType)));
            list = historyRepository.searchHistoryNew(startDate, endDate, pageable);
            historysResponseList = list
                    .stream()
                    .map(HistoryMapping::mapEntityToResponse)
                    .collect(Collectors.toList());
            totalHistory = Math.toIntExact(list.getTotalElements());
        }
        contentResponse.setList(historysResponseList);
        contentResponse.setCurrentPage(currentPage.intValue() + 1);
        contentResponse.setTotalRecord(totalHistory);
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

    public String calcProgressCompleted(int done, int sum, int cancle){
        return done +"/"+ (sum - cancle);
    }

}
