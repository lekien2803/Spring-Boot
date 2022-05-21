package com.baitap02.baitap02.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.baitap02.baitap02.Model.Job;
import com.baitap02.baitap02.Model.Location;
import com.baitap02.baitap02.dto.JobRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.var;

@RestController
@RequestMapping("/job")
public class Controller {
    private ConcurrentHashMap<String, Job> jobs;

    public Controller() {
        jobs = new ConcurrentHashMap<>();
        jobs.put("001", new Job("001", "it", "ngồi coding", "HaNoi", 1000, 4000,
                "alskndla@alknsdlkansd.com"));
        jobs.put("002", new Job("002", "ai-ti", "tester", "DaNang", 2000, 5400,
                "alskndla@alknsdlkansd.com"));
    }

    @GetMapping
    public List<Job> show() {
        return jobs.values().stream().toList();
    }

    @PostMapping
    public Job createNewJob(@RequestBody JobRequest jobRequest) {
        String uuid = UUID.randomUUID().toString();
        Job newJob = new Job(uuid, jobRequest.title(), jobRequest.discription(), jobRequest.location(),
                jobRequest.min_salary(), jobRequest.max_salary(), jobRequest.email_to());
        jobs.put(uuid, newJob);
        return newJob;
    }

    @GetMapping(value = "/{id}")
    public Job getJobById(@PathVariable("id") String id) {
        return jobs.get(id);
    }

    @PutMapping(value = "/{id}")
    public Job updateJobById(@PathVariable("id") String id, @RequestBody JobRequest jobRequest) {
        Job updateJob = new Job(id, jobRequest.title(), jobRequest.discription(), jobRequest.location(),
                jobRequest.min_salary(), jobRequest.max_salary(), jobRequest.email_to());
        jobs.replace(id, updateJob);
        return updateJob;
    }

    @DeleteMapping(value = "/{id}")
    public Job deleteJobById(@PathVariable("id") String id) {
        Job removeJob = jobs.remove(id);
        return removeJob;

    }

    @GetMapping(value = "/search/{keyword}")
    public List<Job> searhByKeyword(@PathVariable("keyword") String keyword) {
        List<Job> byKeyword = new ArrayList<>();
        for (Entry<String, Job> entry : jobs.entrySet()) {
            if (entry.getValue().getTitle().toLowerCase().contains(keyword)
                    || entry.getValue().getDiscription().toLowerCase().contains(keyword)) {
                byKeyword.add(entry.getValue());
            }
        }
        return byKeyword;
    }

    @GetMapping(value = "/salary/{salary}")
    public List<Job> searchBySalary(@PathVariable("salary") int salary) {
        List<Job> bySalary = new ArrayList<>();
        for (Entry<String, Job> entry : jobs.entrySet()) {
            if (entry.getValue().getMin_salary() <= salary && entry.getValue().getMax_salary() >= salary) {
                bySalary.add(entry.getValue());
            }
        }
        return bySalary;
    }

    @GetMapping(value = "/{sortbylocation}")
    public List<Job> sortByLocation() {
        List<Job> byLocation = new ArrayList<>();

        for (Entry<String, Job> entry : jobs.entrySet()) {
            byLocation.add(entry.getValue());
        }

        Collections.sort(byLocation, new Comparator<Job>() {

            @Override
            public int compare(Job o1, Job o2) {
                return o1.getLocation().compareTo(o2.getLocation());
            }

        });

        return byLocation;
    }

    @GetMapping(value = "/query")
    public List<Job> searchByTAndL(@RequestParam("location") String location, @RequestParam("title") String title) {
        List<Job> byTAndL = new ArrayList<>();

        for (Entry<String, Job> entry : jobs.entrySet()) {
            if (entry.getValue().getLocation().toLowerCase().equals(location)
                    && (entry.getValue().getTitle().toLowerCase().contains(title)
                            || entry.getValue().getDiscription().toLowerCase().contains(title))) {
                byTAndL.add(entry.getValue());
            }
        }

        return byTAndL;
    }
}
