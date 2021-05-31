package com.example.demo.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="appointment",catalog="medicalapp")

public class Appointment {
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
    private Date date;
    private Time time;

    @JsonBackReference("medic-appointment")
    @ManyToOne
    @JoinColumn(name = "medic")
    private Medic medic;

    @JsonBackReference("patient-appointment")
    @ManyToOne
    @JoinColumn(name = "patient")
    private Patient patient;
}
