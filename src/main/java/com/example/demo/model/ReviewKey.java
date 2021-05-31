package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ReviewKey implements Serializable{
    @Column(name = "patient")
    private int patientId;
 
    @Column(name = "medic")
    private int medicId;

}
