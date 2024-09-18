package com.example.qllh.Repositories;

import com.example.qllh.Entities.History;
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
public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query("select j from History j where j.diagnostic like %:key% ")
    Page<History> searchHistory(@Param("key") String textSearch, Pageable pageable);

    @Query("select j from History j where  j.examinationDate between :startDate and :endDate")
    Page<History> searchHistoryNew(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate, Pageable pageable);

    @Query("select count(j) from History j where j.status = 'DA_HOAN_THANH_KHAM'")
    int countDone();

    @Query("select count(j) from History j")
    int countSum();

    @Query("select count(j) from History j where  j.status = 'HUY_LICH_KHAM'")
    int countCancel();
}
