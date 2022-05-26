package com.odas.odas.controller;

import java.util.List;

import com.odas.odas.entity.AppoinmentDate;
import com.odas.odas.entity.Doctor;
import com.odas.odas.payload.ApiResponse;
import com.odas.odas.services.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/allDoctor")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> ls = this.doctorService.getAllDoctor();
        if (ls.size() <= 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(ls);
    }

    @GetMapping("/singleDoctor/{docId}")
    public ResponseEntity<Doctor> getSingleDoctor(@PathVariable("docId") int id) {
        return ResponseEntity.ok(this.doctorService.getDoctorById(id));
    }

    @PutMapping("/editDoctor/{docId}")
    public ResponseEntity<Doctor> editDoctor(@PathVariable("docId") int id, @RequestBody Doctor doc) {
        Doctor d = this.doctorService.updateDoctor(id, doc);
        return new ResponseEntity<Doctor>(d, HttpStatus.OK);
    }

    @PutMapping("/editAppoinment/{appId}")
    public ResponseEntity<AppoinmentDate> editDoctor(@PathVariable("appId") int id, @RequestBody AppoinmentDate date) {
        AppoinmentDate d = this.doctorService.updateAppoinmentDate(id, date);
        return new ResponseEntity<AppoinmentDate>(d, HttpStatus.OK);
    }

    @PostMapping("/addDoctor")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doc) {
        return new ResponseEntity<Doctor>(doctorService.addDoctor(doc), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteDoctor/{docId}")
    public ResponseEntity<ApiResponse> deleteDoctor(@PathVariable("docId") int id) {
        this.doctorService.deleteDoctor(id);
        String message = "Doctor with id: " + id + " deleted from the database";
        return new ResponseEntity<ApiResponse>(new ApiResponse(message, true), HttpStatus.OK);
    }
}
