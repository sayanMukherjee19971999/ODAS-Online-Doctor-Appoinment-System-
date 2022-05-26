package com.odas.odas.services;

import java.util.List;

import com.odas.odas.entity.Patient;

public interface PatientService {

    public Patient getPatientById(int id);

    public List<Patient> getAllPatient();

    public Patient addPatient(Patient patient);

    public Patient updatePatient(int id, Patient patient);

    public void deletePatient(int id);

}
