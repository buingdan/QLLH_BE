package com.example.qllh.Services.HistoryService;


import com.example.qllh.DTO.HistoryDTO.HistoryRequest;
import com.example.qllh.DTO.HistoryDTO.HistoryResponse;
import com.example.qllh.Entities.History;
import com.example.qllh.Mapping.HistoryMapping;
import com.example.qllh.Model.ContentResponse;
import com.example.qllh.Model.ResponseApi;
import com.example.qllh.Repositories.HistoryRepository;
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
public class HistoryService implements IHistoryService {
    @Autowired
    private HistoryRepository historyRepository;

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
            historyRepository.save(historyEntityList);
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
            historyEntity.setInsuranceCode(historyRequest.getInsuranceCode());
            historyEntity.setStartDate(historyRequest.getStartDate());
            historyEntity.setEndDate(historyRequest.getEndDate());


            historyRepository.save(historyEntity);
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
            historyRepository.softDelete(id);
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
