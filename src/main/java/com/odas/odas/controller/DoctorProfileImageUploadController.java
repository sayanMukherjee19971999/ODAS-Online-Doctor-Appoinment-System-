package com.odas.odas.controller;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/doctor")
public class DoctorProfileImageUploadController {

    @Autowired
    private DoctorProfileImageUpload docImgUpload;

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadProfilePicture(@RequestParam int id,
            @RequestParam("file") MultipartFile file) {
        DoctorProfileImage docProImg = docImgUpload.saveFileInDb(id, file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(docProImg.getFileName())
                .toUriString();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("File Uploaded Successfully. You can download the file from " + fileDownloadUri);
    }

    @GetMapping("/downloadImage")
    public void downloadFile(@RequestParam int id, HttpServletResponse response)
            throws Exception {
        response.getOutputStream().write(fileContent(id, response));
    }

    private byte[] fileContent(int id, HttpServletResponse response) {
        return docImgUpload.findImgByDoctorId(id, response);
    }

}
