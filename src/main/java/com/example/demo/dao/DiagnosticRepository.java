package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Diagnostic;

@Repository
public interface DiagnosticRepository extends CrudRepository<Diagnostic,Integer>{
    
}
