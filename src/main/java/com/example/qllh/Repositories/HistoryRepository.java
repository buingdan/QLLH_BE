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

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query("select j from History j where j.diagnostic like %:key%")
    Page<History> searchHistory(@Param("key") String textSearch, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update History j set j.isActive = false where j.id = :id")
    int softDelete(@Param("id") Long id);
}
