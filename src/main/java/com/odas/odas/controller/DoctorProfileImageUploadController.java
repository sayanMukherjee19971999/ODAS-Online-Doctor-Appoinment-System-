package com.odas.odas.controller;

import com.odas.odas.entity.DoctorProfileImage;
import com.odas.odas.services.DoctorProfileImageUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/doctor")
public class DoctorProfileImageUploadController {

    @Autowired
    private DoctorProfileImageUpload docImgUpload;

    @PostMapping("/uploadImage")
    public ResponseEntity<DoctorProfileImage> uploadProfilePicture(@RequestParam int id,
            @RequestParam("file") MultipartFile file) {
        DoctorProfileImage docProImg = docImgUpload.saveFileInDb(id, file);
        if (docProImg != null) {
            return ResponseEntity.status(HttpStatus.OK).body(docProImg);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/downloadImage")
    public ResponseEntity<DoctorProfileImage> getFileById(@RequestParam int id) {
        return new ResponseEntity<DoctorProfileImage>(docImgUpload.findImgByDoctorId(id), HttpStatus.OK);

    }

}
