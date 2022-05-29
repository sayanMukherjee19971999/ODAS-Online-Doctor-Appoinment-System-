package com.odas.odas.services;

import javax.servlet.http.HttpServletResponse;

import com.odas.odas.entity.DoctorProfileImage;

import org.springframework.web.multipart.MultipartFile;

public interface DoctorProfileImageUpload {

    public DoctorProfileImage saveFileInDb(int doctor_id, MultipartFile file);

    public byte[] findImgByDoctorId(int doctor_id, HttpServletResponse request);

}
