package com.example.qllh.Repositories;

import com.example.qllh.Entities.MedicalRecords;
import com.example.qllh.Entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordsRepository extends JpaRepository<MedicalRecords, Long> {
    @Query("select j from MedicalRecords j where j.userId.fullName like %:key%")
    Page<MedicalRecords> searchMedicalRecords(@Param("key") String textSearch, Pageable pageable);
}
