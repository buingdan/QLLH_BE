package com.example.qllh.Controllers;

import com.example.qllh.DTO.AppointmentDTO.AppointmentRequest;
import com.example.qllh.Services.AppointmentService.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("")
    public ResponseEntity<?> getAppointments() {
        return new ResponseEntity<>(appointmentService.getAppointments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable Long id) {
        return new ResponseEntity<>(appointmentService.getAppointmentsById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        return new ResponseEntity<>(appointmentService.addAppointment(appointmentRequest), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequest appointmentRequest) {
        return new ResponseEntity<>(appointmentService.updateAppointment(id, appointmentRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        return new ResponseEntity<>(appointmentService.deleteAppointment(id), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getAppointment(@RequestParam(value = "textSearch", defaultValue = "") String textSearch,
                                        @RequestParam(value = "sortData", defaultValue = "id") String sortData,
                                        @RequestParam(value = "sortType", defaultValue = "asc") String sortType,
                                        @RequestParam(value = "currentPage", defaultValue = "1") Long currentPage,
                                        @RequestParam(value = "limit", defaultValue = "1") Long limit) {
        return new ResponseEntity<>(appointmentService.getPageAppointment(textSearch, currentPage, limit, sortData, sortType), HttpStatus.OK);
    }
}
