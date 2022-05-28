package com.odas.odas.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorProfileImage {
    @Id
    private int id;
    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Doctor doctor;

}
