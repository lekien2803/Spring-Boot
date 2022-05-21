package vn.techmaster.jobhunt.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import vn.techmaster.jobhunt.model.Employer;

@Repository
public class EmployerRepository {
    private static ConcurrentHashMap<String,Employer> employers = new ConcurrentHashMap<>();

    public Collection<Employer> getEmployer(){
        return employers.values();
    }

    public Employer addEmployer(Employer employer){
        String uuid = UUID.randomUUID().toString();

        employer.setId(uuid);
        employers.put(uuid, employer);
        return employer;
    }

    public Employer findById(String id){
        return employers.get(id);
    }

    @PostConstruct
    public void addData(){
        this.addEmployer(Employer.builder()
        .name("Google")
        .website("https://google.com")
        .email("apply@google.com").build());

        this.addEmployer(Employer.builder()
        .name("Facebook")
        .website("https://facebook.com")
        .email("apply@facebook.com").build());

        this.addEmployer(Employer.builder()
        .name("Twitter")
        .website("https://twitter.com")
        .email("apply@twitter.com").build());

        this.addEmployer(Employer.builder()
        .name("Apple")
        .website("https://apple.com")
        .email("apply@apple.com").build());
    }

    public Employer deleteById(String id){
        return employers.remove(id);
    }

    
}
