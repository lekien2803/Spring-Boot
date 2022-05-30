package com.example.demo.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import com.example.demo.model.Employer;

import org.springframework.stereotype.Repository;

@Repository
public class EmployerRepo {
    private ConcurrentHashMap<String, Employer> employers = new ConcurrentHashMap<>();

    public EmployerRepo(){

    }

    public Employer addEmployer(Employer employer){
        String id = UUID.randomUUID().toString();
        employer.setId(id);
        employers.put(id, employer);
        return employer;
    }

    public Collection<Employer> showAllEmployer(){
        return employers.values();
    }

    public Employer findByID(String id){
        return employers.get(id);
    }

    public Employer deleteByID(String id){
        return employers.remove(id);
    }

    @PostConstruct
    public void addSomeData(){
        this.addEmployer(Employer.builder()
                .name("Google")
                .logo_path("google.png")
                .website("https://google.com")
                .email("apply@google.com").build());

        this.addEmployer(Employer.builder()
                .name("Facebook")
                .logo_path("facebook.png")
                .website("https://facebook.com")
                .email("apply@facebook.com").build());

        this.addEmployer(Employer.builder()
                .name("Twitter")
                .logo_path("Twitter.png")
                .website("https://twitter.com")
                .email("apply@twitter.com").build());

        this.addEmployer(Employer.builder()
                .name("Amazon")
                .logo_path("Amazon.png")
                .website("https://Amazon.com")
                .email("apply@Amazon.com").build());
    }
    
}
