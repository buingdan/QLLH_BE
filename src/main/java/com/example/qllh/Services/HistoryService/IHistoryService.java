package com.example.qllh.Services.HistoryService;

import com.example.qllh.DTO.HistoryDTO.HistoryRequest;
import com.example.qllh.Model.ContentResponse;
import com.example.qllh.Model.ResponseApi;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface IHistoryService {
    ResponseApi getHistory();
    ResponseApi getHistoryById(Long id);
    ResponseApi addHistory(HistoryRequest historyRequest);

    ResponseApi updateHistory(Long id, HistoryRequest historyRequest);

    ResponseApi deleteHistory(Long id);

    ContentResponse getPageHistory(String textSearch, Long currentPage, Long limit, String sortData, String sortType);

    ContentResponse getPageHistoryNew(LocalDate startDate, LocalDate endDate, Long currentPage, Long limit, String sortData, String sortType);
}
