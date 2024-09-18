package com.example.qllh.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Users patientId;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Users doctorId;

    @Column(name = "patient_name")
    private String patientName;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "appointment_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate appointmentTime;
}
