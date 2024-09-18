package com.example.qllh.Repositories;

import com.example.qllh.Entities.History;
import com.example.qllh.Entities.MedicalRecords;
import com.example.qllh.Entities.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MedicalRecordsRepository extends JpaRepository<MedicalRecords, Long> {
//    @Query("select j from MedicalRecords j where j.userId.fullName like %:key%")
//    Page<MedicalRecords> searchMedicalRecords(@Param("key") String textSearch, Pageable pageable);

    @Query("select j from MedicalRecords j where  j.startDate between :startDate and :endDate AND j.endDate between :startDate and :endDate and j.userId.fullName like %:patientSearch% and j.insuranceCode like %:insuranceSearch% and j.isActive = true")
    Page<MedicalRecords> searchMedicalRecords(@Param("patientSearch") String patientSearch, @Param("insuranceSearch") String insuranceSearch, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, Pageable pageable);
    @Modifying
    @Transactional
    @Query("update MedicalRecords j set j.isActive = false where j.id = :id")
    int softDelete(@Param("id") Long id);

}
