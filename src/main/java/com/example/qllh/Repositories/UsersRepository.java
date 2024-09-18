package com.example.qllh.Repositories;

import com.example.qllh.Entities.Appointments;
import com.example.qllh.Entities.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("select j from Users j where j.fullName like %:key%")
    Page<Users> searchUser(@Param("key") String textSearch, Pageable pageable);

    @Query("select j from Users j where j.fullName like %:name% and j.phoneNumber like %:phoneSearch% and j.address like %:address% and j.gender in :gender and j.role like %:role% and j.isActive = true")
    Page<Users> searchUserNew(@Param("name") String nameSearch, @Param("phoneSearch") String phoneSearch, @Param("address") String addressSearch, @Param("gender") List<Boolean> genderSearch, @Param("role") String roleSearch, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Users j set j.isActive = false where j.id = :id")
    int softDelete(@Param("id") Long id);

    @Query("select j from Users j where j.role like 'Bệnh nhân'")
    List<Users>findPatients();

    @Query("select j from Users j where j.role like 'Bác sĩ'")
    List<Users>findDoctors();
}
