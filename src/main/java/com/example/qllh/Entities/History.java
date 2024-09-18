package com.example.qllh.Entities;

import com.example.qllh.Enum.ExaminationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "insurance_code")
//    private String insuranceCode;
//
//    @Column(name = "diagnostic")
//    private String diagnostic;
//
//    @Column(name = "start_date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private LocalDate startDate;
//
//    @Column(name = "end_date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private LocalDate endDate;
//
//    @Column(name = "text_note")
//    private String textNote;
//
//    @Column(name = "is_active")
//    private boolean isActive;
//
//    @ManyToOne
//    @JoinColumn(name = "medicalRecord_id")
//    private MedicalRecords medicalRecordsId;
//
//    @Column(name = "status")
//    private boolean status;

    @Column(name = "diagnostic")
    private String diagnostic;

    @Column(name = "medical_condition")
    private String medicalCondition;

    @Column(name = "test_results")
    private String testResults;

    @Column(name = "examination_date")
    private LocalDate examinationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ExaminationStatus status;

    @ManyToOne
    @JoinColumn(name = "medicalRecord_id")
    private MedicalRecords medicalRecordsId;

}
