package com.example.hellojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hellojpa.model.Employer;

public interface EmployerRepo extends JpaRepository<Employer, Long> {
    
}
