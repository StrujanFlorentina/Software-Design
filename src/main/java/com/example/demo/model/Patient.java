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

import com.example.demo.ObserverDP.Observer;
import com.example.demo.ObserverDP.Subject;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="patient",catalog="medicalapp")
public class Patient extends Observer {
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

	public Patient(Subject subject){
		this.subject = subject;
		//asta il adauga la lista de observeri
		this.subject.attach(this);
	} 

	@Override
	public void update() {
		System.out.println("The patient "+subject.getState().getState1()+" gave a review to doctor "+subject.getState().getState2());
	}

	@JsonManagedReference("patient-diagnostic")
	@OneToMany(mappedBy = "patient", orphanRemoval = true)
	private List<Diagnostic> diagnostics = new ArrayList<>();

	@JsonManagedReference("patient-appointment")
	@OneToMany(mappedBy = "patient", orphanRemoval = false)
	private List<Appointment> appointments = new ArrayList<>();
}
