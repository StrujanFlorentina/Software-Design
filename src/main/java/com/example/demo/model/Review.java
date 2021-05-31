package com.example.demo.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="review",catalog="medicalapp")

public class Review {

    @EmbeddedId
	private ReviewKey id;

    private int note;
    private String comment;

    @JsonBackReference("medic-review")
    @ManyToOne
    @MapsId("medic")
    @JoinColumn(name = "medic")
    private Medic medic;

    @JsonBackReference("patient-review")
    @ManyToOne
    @MapsId("patient")
    @JoinColumn(name = "patient")
    private Patient patient; 
}
