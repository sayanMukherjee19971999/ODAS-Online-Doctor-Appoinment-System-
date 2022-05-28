package com.odas.odas.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctor_id;
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private String username;
    private String emailId;
    private String contact;
    private String yearOfExp;
    private String speciality;
    private String fees;
    private String password;
    private String retypePassword;

    @Lob
    private byte[] data;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = AppoinmentDate.class, mappedBy = "doctor", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<AppoinmentDate> appDate;

}
