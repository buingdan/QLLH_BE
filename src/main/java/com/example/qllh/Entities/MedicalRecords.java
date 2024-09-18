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
@Table(name = "medical_records")
public class MedicalRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private Users userId;
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
//    @Column(name = "full_name")
//    private String fullName;
//
//    @Column(name = "insurance_code")
//    private String insuranceCode;
//
//    @Column(name = "status")
//    private boolean status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users userId;

    @Column(name = "diagnostic")
    private String diagnostic;

    @Column(name = "start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name = "text_note")
    private String textNote;

    @Column(name = "insurance_code")
    private String insuranceCode;

    @Column(name = "progress_completed")
    private String progressCompleted;

    @Column(name = "is_active")
    private boolean isActive;
}
