package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="medic",catalog="medicalapp")
public class Medic {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
    @Column(unique=true)
	private String email;
    @Column(unique=true)
	private String password;
	private String address;
	private int age;
	private int phone;
    private String specialization;
    private int seniority;

	@JsonManagedReference("medic-review")
	@OneToMany(mappedBy = "medic", orphanRemoval = true)
	private List<Review> reviews = new ArrayList<>();
	
    @JsonManagedReference("medic-appointment")
	@OneToMany(mappedBy = "medic", orphanRemoval = true)
	private List<Appointment> appointments = new ArrayList<>();
}
