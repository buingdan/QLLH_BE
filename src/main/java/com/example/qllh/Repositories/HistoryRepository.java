package com.example.qllh.Repositories;

import com.example.qllh.Entities.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query("select j from History j where j.diagnostic like %:key%")
    Page<History> searchHistory(@Param("key") String textSearch, Pageable pageable);
}
