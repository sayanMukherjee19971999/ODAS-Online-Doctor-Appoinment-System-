package com.odas.odas.repository;

import com.odas.odas.entity.AppoinmentDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppoinmentDateRepo extends JpaRepository<AppoinmentDate, Integer> {

    @Query(value = "select doctor_id From appoinment_date WHERE date_id=:id", nativeQuery = true)
    public int getDoctor_id(@Param("id") int date_id);
}
