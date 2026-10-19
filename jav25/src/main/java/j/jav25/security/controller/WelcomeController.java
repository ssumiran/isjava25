package j.jav25.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Hello, World! test";
    }
    
    @GetMapping("/user")
    public String userTest(){
        return "User test";
    }
    
    @GetMapping("/admin")
    public String adminTest(){
        return "Admin test";
    }
}
