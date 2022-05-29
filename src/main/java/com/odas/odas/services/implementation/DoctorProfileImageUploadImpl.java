package com.odas.odas.services.implementation;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.odas.odas.entity.Doctor;
import com.odas.odas.entity.DoctorProfileImage;
import com.odas.odas.exception.DocumentUploadException;

import com.odas.odas.exception.ResourceNotFoundException;

import com.odas.odas.repository.DoctorProfileImageRepository;
import com.odas.odas.repository.DoctorRepository;
import com.odas.odas.services.DoctorProfileImageUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DoctorProfileImageUploadImpl implements DoctorProfileImageUpload {

    @Autowired
    private DoctorRepository docRepo;

    @Autowired
    private DoctorProfileImageRepository docImgRepo;

    @Override
    public DoctorProfileImage saveFileInDb(int doctor_id, MultipartFile file)
            throws DocumentUploadException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Doctor doctor = this.docRepo.findById(doctor_id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "Id", doctor_id));

        try {
            DoctorProfileImage docImg = new DoctorProfileImage(doctor_id, fileName, file.getContentType(),
                    file.getBytes(),
                    doctor);

            return docImgRepo.save(docImg);
        } catch (IOException e) {
            throw new DocumentUploadException("Doctor Profile Image", "File Name", fileName);
        } catch (MaxUploadSizeExceededException me) {
            long size = file.getSize();
            throw new MaxUploadSizeExceededException(size);
        }

    }

    @Override
    public byte[] findImgByDoctorId(int doctor_id, HttpServletResponse request) {
        DoctorProfileImage doc = docImgRepo.findById(doctor_id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor Profile Picture", "Image Id", doctor_id));
        request.setHeader("Content-Disposition", "attachment; filename=\"" + doc.getFileName() + "\"");
        return doc.getData();
    }

}
