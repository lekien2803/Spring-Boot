package com.jobhunt.jobhunt.Controller;

import java.io.IOException;

import javax.validation.Valid;

import com.jobhunt.jobhunt.model.Employer;
import com.jobhunt.jobhunt.repository.EmployerRepo;
import com.jobhunt.jobhunt.request.EmployerReq;
import com.jobhunt.jobhunt.service.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/employer")
public class EmployerController {
    @Autowired
    private EmployerRepo emRepo;
    @Autowired
    private StorageService stoServ;

    @GetMapping
    public String getAllEmployer(Model model) {
        model.addAttribute("employers", emRepo.showAllEmployer());
        return "employer_list";
    }

    @GetMapping(value = "/{id}")
    public String findByID(Model model, @PathVariable("id") String id) {
        model.addAttribute("employer", emRepo.findByID(id));
        return "employer";
    }

    @GetMapping(value = "/add")
    public String addEmployerForm(Model model) {
        model.addAttribute("employer", new EmployerReq("", null, "", ""));
        return "employer_add";
    }

    @PostMapping(value = "/add", consumes = { "multipart/form-data" })
    public String addEmployer(@Valid @ModelAttribute("employer") EmployerReq emReq, BindingResult result, Model model) {
        if (emReq.logo_path().getOriginalFilename().isEmpty()) {
            result.addError(new FieldError("employer", "logo", "Logo is required"));
        }

        if (result.hasErrors()) {
            System.out.println("ERROR: " + result.toString());
            return "employer_add";
        }

        Employer emp = emRepo.addEmployer(Employer.builder()
                .name(emReq.name())
                .website(emReq.website())
                .email(emReq.email()).build());

        try {
            String saveFileName = stoServ.saveFile(emReq.logo_path(), emp.getId());
            emRepo.updateLogo(emp.getId(), saveFileName);
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        return "redirect:/employer";
    }

    @GetMapping(value = "delete/{id}")
    public String deleteEmployer(@PathVariable("id") String id) {
        Employer emp = emRepo.deleteByID(id);
        stoServ.deleteFile(emp.getLogo_path());
        return "redirect:/employer";
    }

    @GetMapping(value = "/edit/{id}")
    public String editEmployer(@PathVariable("id") String id, @RequestBody Employer employer, Model model) {
        emRepo.editByID(id, employer);
        return "employer_edit";
    }
}
