package com.odas.odas.services;

import java.util.List;

import com.odas.odas.entity.AppoinmentDate;
import com.odas.odas.entity.Doctor;

public interface DoctorService {

    public List<Doctor> getAllDoctor();

    public Doctor getDoctorById(int id);

    public Doctor addDoctor(Doctor doctor);

    public Doctor updateDoctor(int id, Doctor doctor);

    public AppoinmentDate updateAppoinmentDate(int id, AppoinmentDate date);

    public void deleteDoctor(int id);

}
