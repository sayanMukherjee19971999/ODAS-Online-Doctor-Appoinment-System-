package com.odas.odas.repository;

import com.odas.odas.entity.DoctorProfileImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoctorProfileImageRepository extends JpaRepository<DoctorProfileImage, Integer> {

    @Query(value = "select id from doctor_profile_image where doctor_doctor_id=:id", nativeQuery = true)
    public int getImageId(@Param("id") int doctor_id);
}
