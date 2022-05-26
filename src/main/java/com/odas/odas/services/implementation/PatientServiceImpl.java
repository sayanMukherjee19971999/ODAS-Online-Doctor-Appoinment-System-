package com.odas.odas.services.implementation;

import java.util.List;

import com.odas.odas.entity.Patient;
import com.odas.odas.exception.ResourceNotFoundException;
import com.odas.odas.repository.PatientRepository;
import com.odas.odas.services.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepo;

    @Override
    public Patient getPatientById(int id) {
        Patient p = this.patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "Patient Id", id));
        return p;
    }

    @Override
    public List<Patient> getAllPatient() {
        return this.patientRepo.findAll();
    }

    @Override
    public Patient addPatient(Patient patient) {
        return this.patientRepo.save(patient);
    }

    @Override
    public Patient updatePatient(int id, Patient patient) {
        Patient p = this.patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "Patient Id", id));
        p.setContact(patient.getContact());
        p.setDob(patient.getDob());
        p.setEmailId(patient.getEmailId());
        p.setFirstName(patient.getFirstName());
        p.setGender(patient.getGender());
        p.setLastName(patient.getLastName());
        p.setPassword(patient.getPassword());
        p.setRetypePassword(patient.getRetypePassword());
        p.setUsername(patient.getUsername());
        return p;
    }

    @Override
    public void deletePatient(int id) {
        Patient p = this.patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "Patient Id", id));
        this.patientRepo.delete(p);
    }

}
