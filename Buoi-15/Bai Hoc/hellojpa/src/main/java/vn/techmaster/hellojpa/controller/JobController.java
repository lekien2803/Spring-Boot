package vn.techmaster.hellojpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.hellojpa.model.Job;
import vn.techmaster.hellojpa.repository.JobRepo;

@RestController
@RequestMapping("/api/job")
public class JobController {
    @Autowired
    private JobRepo jobRepo;

    @GetMapping
    public List<Job> getAllJob() {
        return jobRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Job> getJobById(@PathVariable("id") Long id) {
        return jobRepo.findById(id);
    }

}
