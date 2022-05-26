package com.jobhunt.jobhunt.repository;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import com.jobhunt.jobhunt.model.Employer;
import com.jobhunt.jobhunt.request.EmployerReq;

import org.springframework.stereotype.Repository;

@Repository
public class EmployerRepo {
    private ConcurrentHashMap<String, Employer> employers = new ConcurrentHashMap<>();

    public EmployerRepo() {

    }

    public Collection<Employer> showAllEmployer() {
        return employers.values();
    }

    public Employer addEmployer(Employer employer) {
        String uuid = UUID.randomUUID().toString();

        employer.setId(uuid);
        employers.put(uuid, employer);
        return employer;
    }

    public Employer findByID(String id) {
        return employers.get(id);
    }

    public Employer deleteByID(String id) {
        return employers.remove(id);
    }

    public void editByID(String id, Employer employer) {
        employers.putIfAbsent(id, employer);
    }

    public void updateLogo(String id, String logo_path) {
        var emp = employers.get(id);
        emp.setLogo_path(logo_path);
        employers.put(id, emp);
    }

    @PostConstruct
    public void addSomeData() {
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
