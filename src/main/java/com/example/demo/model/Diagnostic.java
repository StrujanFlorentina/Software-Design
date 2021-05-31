package com.example.demo.model;

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
@Table(name="diagnostic",catalog="medicalapp")

public class Diagnostic {
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
    private String name;

    @JsonBackReference("patient-diagnostic")
    @ManyToOne
    @JoinColumn(name = "patient")
    private Patient patient;
}
