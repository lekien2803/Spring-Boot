package vn.techmaster.jobhunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.repository.EmployerRepository;
import vn.techmaster.jobhunt.request.EmployerRequest;

@Controller
@RequestMapping(value = "/employer")
public class EmployerController {
    @Autowired
    private EmployerRepository emRepo;

    @GetMapping("/add")
    public String addEmployerForm(Model model) {
        model.addAttribute("employer", new EmployerRequest("","","","",""));
        return "employer_add";
    }

    @PostMapping(value = "/add")
    public String addEmployer(@ModelAttribute EmployerRequest employerRequest, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "employer_add";
        }
        System.out.println(employerRequest);
        return "redirect:/employer_list";
    }

    @GetMapping("/list")
    public String showEmployer(Model model) {
        model.addAttribute("employers", emRepo.getEmployer());
        return "employer_list";
    }

    @GetMapping(value = "/{id}")
    public String showEmployerById(Model model, @PathVariable String id){
        model.addAttribute("employer", emRepo.findById(id));
        return "employer";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteByID(Model model, @PathVariable String id){
        model.addAttribute("employer", emRepo.deleteById(id));
        return "employer_list";
    }
}
