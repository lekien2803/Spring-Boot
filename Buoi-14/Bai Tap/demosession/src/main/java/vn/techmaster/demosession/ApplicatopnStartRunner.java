package vn.techmaster.demosession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import vn.techmaster.demosession.service.UserService;

@Component
public class ApplicatopnStartRunner implements ApplicationRunner {
    @Autowired UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
       userService.addUserThenAutoActivate("admin", "admin@a.com", "12345");
       userService.addUser("admin2", "admin2@a.com", "abc123");
        
    }
    
}
