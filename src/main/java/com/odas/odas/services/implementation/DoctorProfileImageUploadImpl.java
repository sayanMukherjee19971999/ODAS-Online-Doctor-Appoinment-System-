package com.odas.odas.services.implementation;

import com.odas.odas.entity.Doctor;
import com.odas.odas.entity.DoctorProfileImage;
import com.odas.odas.exception.ResourceNotFoundException;
import com.odas.odas.repository.DoctorProfileImageRepository;
import com.odas.odas.repository.DoctorRepository;
import com.odas.odas.services.DoctorProfileImageUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DoctorProfileImageUploadImpl implements DoctorProfileImageUpload {

    @Autowired
    private DoctorRepository docRepo;

    @Autowired
    private DoctorProfileImageRepository docImgRepo;

    @Override
    public DoctorProfileImage saveFileInDb(int doctor_id, MultipartFile file) {

        String fileName = file.getOriginalFilename();

        Doctor doctor = this.docRepo.findById(doctor_id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "Id", doctor_id));

        try {
            DoctorProfileImage docImg = new DoctorProfileImage(doctor_id, fileName, file.getContentType(),
                    file.getBytes(),
                    doctor);

            return docImgRepo.save(docImg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public DoctorProfileImage findImgByDoctorId(int doctor_id) {
        int imgId = docImgRepo.getImageId(doctor_id);
        return docImgRepo.findById(imgId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor Profile Image", "Id", imgId));

    }

}
