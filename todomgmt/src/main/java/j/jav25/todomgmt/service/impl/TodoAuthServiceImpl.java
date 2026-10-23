package j.jav25.todomgmt.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import j.jav25.todomgmt.dto.TodoLoginDto;
import j.jav25.todomgmt.dto.TodoRegisterDto;
import j.jav25.todomgmt.entity.TodoRole;
import j.jav25.todomgmt.entity.TodoUser;
import j.jav25.todomgmt.exception.TodoApiException;
import j.jav25.todomgmt.repository.TodoRoleRepository;
import j.jav25.todomgmt.repository.TodoUserRepository;
import j.jav25.todomgmt.service.TodoAuthService;

@Service
public class TodoAuthServiceImpl implements TodoAuthService{

	private TodoUserRepository todoUserRepository;
	private TodoRoleRepository todoRoleRepository;
	private AuthenticationManager authenticationManager;
	private PasswordEncoder passwordEncoder;
	
	public TodoAuthServiceImpl (TodoUserRepository todoUserRepository,
								TodoRoleRepository todoRoleRepository,
								AuthenticationManager authenticationManager,
								PasswordEncoder passwordEncoder
								) {
		this.todoUserRepository = todoUserRepository;
		this.todoRoleRepository = todoRoleRepository;
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public String register(TodoRegisterDto registerDto) {
		// check username is already exists in database
        if(todoUserRepository.existsByUsername(registerDto.username())){
            throw new TodoApiException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        // check email is already exists in database
        if(todoUserRepository.existsByEmail(registerDto.email())){
            throw new TodoApiException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        TodoUser todoUser = new TodoUser();
        todoUser.setName(registerDto.name());
        todoUser.setUsername(registerDto.username());
        todoUser.setEmail(registerDto.email());
        todoUser.setPassword(passwordEncoder.encode(registerDto.password()));

        Set<TodoRole> roles = new HashSet<>();
        TodoRole userRole = todoRoleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        todoUser.setTodoRoles(roles);

        todoUserRepository.save(todoUser);

        return "User Registered Successfully!.";
	}

	@Override
	public String login(TodoLoginDto loginDto) {
		Authentication authentication = 
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
					loginDto.usernameOrEmail(),
					loginDto.password()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token =" Here is token" ;
        return token;
	}

}
