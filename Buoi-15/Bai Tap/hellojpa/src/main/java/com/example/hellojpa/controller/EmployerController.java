package com.example.hellojpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hellojpa.model.Employer;
import com.example.hellojpa.repository.EmployerRepo;

@RestController
@RequestMapping("/api/employer")
public class EmployerController {
    @Autowired private EmployerRepo employerRepo;

    @GetMapping
    public List<Employer> showAllEmployer(){
        return employerRepo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<Employer> findEmployerById(@PathVariable("id") Long id){
        return employerRepo.findById(id);
    }

    @DeleteMapping(value = "{id}")
    public void deleteEmplById(@PathVariable("id") Long id){
        employerRepo.deleteById(id);
        
    }
}
