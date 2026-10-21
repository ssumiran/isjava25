package j.jav25.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import j.jav25.security.dto.LoginDto;
import j.jav25.security.dto.RegisterDto;
import j.jav25.security.service.AuthService;

@RestController
@RequestMapping("/security/auth")
public class AuthController {

    private AuthService authService;

    // @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Build Register or Sign Up REST API
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String message = authService.register(registerDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

 // Build Login or sing-in REST API  JwtAuthResponseDto
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        return ResponseEntity.ok(token);
    }
    
}
