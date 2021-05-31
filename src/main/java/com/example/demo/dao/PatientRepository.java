package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient,Integer>{

    @Query("Select p from Patient p where p.email=?1") //?1 pentru primul parametru
    Patient findbyEmail(String email);

    @Query("Select p from Patient p where p.password=?1") //?1 pentru primul parametru
    Patient findbyPassword(String password);
}
