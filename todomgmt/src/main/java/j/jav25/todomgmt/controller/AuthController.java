package j.jav25.todomgmt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import j.jav25.todomgmt.dto.TodoRegisterDto;
import j.jav25.todomgmt.service.TodoAuthService;
import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private TodoAuthService todoAuthService;

    // Build Register REST API
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody TodoRegisterDto registerDto){
        String response = todoAuthService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
