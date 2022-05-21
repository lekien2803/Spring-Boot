package vn.techmaster.jobhunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.jobhunt.repository.EmployerRepository;

@Controller
@RequestMapping("/")
public class HomeController {
  
  @GetMapping
  public String showHomePage() {
    return "index";
  }

  
}
