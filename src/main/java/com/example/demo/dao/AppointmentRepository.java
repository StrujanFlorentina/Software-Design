package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.example.demo.model.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment,Integer>{
    @Query("Select a from Appointment a where a.medic.id=?1") //?1 pentru primul parametru
    List<Appointment> findByMedic(int id);

    @Query("Select a from Appointment a where a.date=?1") //?1 pentru primul parametru
    Appointment findByDate(Date date);

    @Query("Select a from Appointment a where a.time=?1") //?1 pentru primul parametru
    Appointment findByTime(Time time);

}
