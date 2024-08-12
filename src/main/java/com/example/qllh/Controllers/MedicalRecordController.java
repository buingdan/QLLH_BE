package com.example.qllh.Controllers;

import com.example.qllh.DTO.MedicalRecordDTO.MedicalRecordRequest;
import com.example.qllh.Services.MedicalRecordService.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/medical-records")
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("")
    public ResponseEntity<?> getMedicalRecords() {
        return new ResponseEntity<>(medicalRecordService.getMedicalRecords(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMedicalRecordById(@PathVariable Long id) {
        return new ResponseEntity<>(medicalRecordService.getMedicalRecordsById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMedicalRecord(@RequestBody MedicalRecordRequest medicalRecordRequest) {
        return new ResponseEntity<>(medicalRecordService.addMedicalRecord(medicalRecordRequest), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMedicalRecord(@PathVariable Long id, @RequestBody MedicalRecordRequest medicalRecordRequest) {
        return new ResponseEntity<>(medicalRecordService.updateMedicalRecord(id, medicalRecordRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMedicalRecord(@PathVariable Long id) {
        return new ResponseEntity<>(medicalRecordService.deleteMedicalRecord(id), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getMedicalRecords(@RequestParam(value = "textSearch", defaultValue = "") String textSearch,
                                          @RequestParam(value = "sortData", defaultValue = "id") String sortData,
                                          @RequestParam(value = "sortType", defaultValue = "asc") String sortType,
                                          @RequestParam(value = "currentPage", defaultValue = "1") Long currentPage,
                                          @RequestParam(value = "limit", defaultValue = "1") Long limit) {
        return new ResponseEntity<>(medicalRecordService.getPageMedicalRecords(textSearch, currentPage, limit, sortData, sortType), HttpStatus.OK);
    }
}
