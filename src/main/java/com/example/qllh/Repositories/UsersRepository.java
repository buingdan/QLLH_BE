package com.example.qllh.Repositories;

import com.example.qllh.Entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("select j from Users j where j.fullName like %:key%")
    Page<Users> searchUser(@Param("key") String textSearch, Pageable pageable);
}
