package com.example.demo.controller;

import com.example.demo.model.Employer;
import com.example.demo.repository.EmployerRepo;
import com.example.demo.request.EmployerRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.val;

@Controller
@RequestMapping(value = "/employer")
public class EmployerController {
    @Autowired
    private EmployerRepo emRepo;

    @GetMapping
    public String listAllEmployer(Model model) {
        model.addAttribute("employers", emRepo.showAllEmployer());
        return "employer_list";
    }

    @GetMapping(value = "/{id}")
    public String showByID(@PathVariable("id") String id, Model model) {
        model.addAttribute("employer", emRepo.findByID(id));
        return "employer";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteByID(@PathVariable("id") String id) {
        emRepo.deleteByID(id);
        return "redirect:/employer";
    }

    @GetMapping(value = "/add")
    public String addEmployerForm(Model model) {
        model.addAttribute("employer", new EmployerRequest("", null, "", ""));
        return "employer_add";
    }

    @PostMapping(value = "/add", consumes = { "multipart/form-data" })
    public String addEmployer(@ModelAttribute("employer") EmployerRequest eRequest, BindingResult result, Model model) {

        return "redirect:/employer";
    }
}
