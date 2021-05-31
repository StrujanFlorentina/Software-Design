package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.demo.model.Medic;

@Repository
public interface MedicRepository extends CrudRepository<Medic,Integer>{
    @Query("Select p from Medic p where p.email=?1") //?1 pentru primul parametru
    Medic findbyEmail(String email);

    @Query("Select p from Medic p where p.password=?1") //?1 pentru primul parametru
    Medic findbyPassword(String password);

    @Query("Select p from Medic p where p.specialization=?1") //?1 pentru primul parametru
    List<Medic> findBySpecialization(String specialization);
}
