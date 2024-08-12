package com.example.qllh.Controllers;

import com.example.qllh.DTO.HistoryDTO.HistoryRequest;
import com.example.qllh.Services.HistoryService.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("")
    public ResponseEntity<?> getHistory() {
        return new ResponseEntity<>(historyService.getHistory(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHistoryById(@PathVariable Long id) {
        return new ResponseEntity<>(historyService.getHistoryById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addHistory(@RequestBody HistoryRequest historyRequest) {
        return new ResponseEntity<>(historyService.addHistory(historyRequest), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateHistory(@PathVariable Long id, @RequestBody HistoryRequest historyRequest) {
        return new ResponseEntity<>(historyService.updateHistory(id, historyRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHistory(@PathVariable Long id) {
        return new ResponseEntity<>(historyService.deleteHistory(id), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getHistory(@RequestParam(value = "textSearch", defaultValue = "") String textSearch,
                                               @RequestParam(value = "sortData", defaultValue = "id") String sortData,
                                               @RequestParam(value = "sortType", defaultValue = "asc") String sortType,
                                               @RequestParam(value = "currentPage", defaultValue = "1") Long currentPage,
                                               @RequestParam(value = "limit", defaultValue = "1") Long limit) {
        return new ResponseEntity<>(historyService.getPageHistory(textSearch, currentPage, limit, sortData, sortType), HttpStatus.OK);
    }
}
