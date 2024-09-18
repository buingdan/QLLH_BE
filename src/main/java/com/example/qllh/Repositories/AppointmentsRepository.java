package com.example.qllh.Repositories;

import com.example.qllh.Entities.Appointments;
import com.example.qllh.Entities.History;
import com.example.qllh.Entities.MedicalRecords;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {
    @Query("select j from Appointments j where j.doctorId.fullName like %:key%")
    Page<Appointments> searchAppointment(@Param("key") String textSearch, Pageable pageable);

    @Query("select j from Appointments j where  j.appointmentTime between :startDate and :endDate and j.patientName like %:patientSearch% and j.doctorName like %:doctorSearch%")
    Page<Appointments> searchAppointmentNew(@Param("patientSearch") String patientSearch, @Param("doctorSearch") String doctorSearch, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, Pageable pageable);


}
