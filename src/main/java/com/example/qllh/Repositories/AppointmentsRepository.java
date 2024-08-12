package com.example.qllh.Repositories;

import com.example.qllh.Entities.Appointments;
import com.example.qllh.Entities.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {
    @Query("select j from Appointments j where j.doctorId.fullName like %:key%")
    Page<Appointments> searchAppointment(@Param("key") String textSearch, Pageable pageable);
}
