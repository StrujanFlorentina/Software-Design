package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Review;
import com.example.demo.model.ReviewKey;

@Repository
public interface ReviewRepository extends CrudRepository<Review,ReviewKey>{
    
}
