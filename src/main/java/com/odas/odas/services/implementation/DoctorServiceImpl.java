package com.odas.odas.services.implementation;

import java.util.List;

import javax.transaction.Transactional;

import com.odas.odas.entity.AppoinmentDate;
import com.odas.odas.entity.Doctor;
import com.odas.odas.exception.ResourceNotFoundException;
import com.odas.odas.repository.AppoinmentDateRepo;
import com.odas.odas.repository.DoctorRepository;
import com.odas.odas.services.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private AppoinmentDateRepo appoinmentDateRepo;

    @Override
    public List<Doctor> getAllDoctor() {
        return this.doctorRepo.findAll();
    }

    @Override
    public Doctor getDoctorById(int id) {
        Doctor d = this.doctorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "Doctor ID", id));
        return d;
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return this.doctorRepo.save(doctor);
    }

    @Override
    @Transactional
    public Doctor updateDoctor(int id, Doctor doctor) {
        Doctor d = this.doctorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "Doctor ID", id));
        d.setContact(doctor.getContact());
        d.setDob(doctor.getDob());
        d.setEmailId(doctor.getEmailId());
        d.setFees(doctor.getFees());
        d.setFirstName(doctor.getFirstName());
        d.setGender(doctor.getGender());
        d.setLastName(doctor.getLastName());
        d.setPassword(doctor.getPassword());
        d.setRetypePassword(doctor.getRetypePassword());
        d.setSpeciality(doctor.getSpeciality());
        d.setUsername(doctor.getUsername());
        d.setYearOfExp(doctor.getYearOfExp());

        return d;
    }

    @Override
    public void deleteDoctor(int id) {
        Doctor d = this.doctorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "Doctor ID", id));
        this.doctorRepo.delete(d);
    }

    @Override
    @Transactional
    public AppoinmentDate updateAppoinmentDate(int id, AppoinmentDate date) {
        AppoinmentDate appdate = this.appoinmentDateRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appoinment Date", "Appoinment ID", id));
        appdate.setDate(date.getDate());
        int doctor_id = this.appoinmentDateRepo.getDoctor_id(id);
        Doctor d = this.doctorRepo.findById(doctor_id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "Doctor ID", id));
        appdate.setDoctor(d);
        appdate.setEndTime(date.getEndTime());
        appdate.setStartTime(date.getStartTime());
        return appdate;
    }

}
