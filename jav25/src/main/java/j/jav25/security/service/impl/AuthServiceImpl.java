package j.jav25.security.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import j.jav25.security.dto.LoginDto;
import j.jav25.security.dto.RegisterDto;
import j.jav25.security.entity.SecRole;
import j.jav25.security.entity.SecUser;
import j.jav25.security.exception.EmailAlreadyExistsException;
import j.jav25.security.repository.SecRoleRepository;
import j.jav25.security.repository.SecUserRepository;
import j.jav25.security.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private PasswordEncoder passwordEncoder;
	private SecUserRepository secUserRepository;
	private SecRoleRepository secRoleRepository;
	private AuthenticationManager authenticationManager;

	public AuthServiceImpl(PasswordEncoder passwordEncoder, SecUserRepository secUserRepository,
			SecRoleRepository secRoleRepository, AuthenticationManager authenticationManager) {
		this.passwordEncoder = passwordEncoder;
		this.secUserRepository = secUserRepository;
		this.secRoleRepository = secRoleRepository;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public String register(RegisterDto registerDto) {

		if (secUserRepository.existsByUsername(registerDto.username())) {
			throw new UsernameNotFoundException("Username already exists.");
		}
		
		if(secUserRepository.existsByEmail(registerDto.email())){ 
			throw new EmailAlreadyExistsException("Email already exists."); 
		}

		SecUser serUser = new SecUser();
		serUser.setName(registerDto.name());
		serUser.setUsername(registerDto.username());
		serUser.setEmail(registerDto.email());
		serUser.setPassword(passwordEncoder.encode(registerDto.password()));

		Set<SecRole> secRoles = new HashSet<>();
		for (String r : registerDto.roles()) {
			secRoles.add(secRoleRepository.findByName(r));
		}

		serUser.setSecRoles(secRoles);

		secUserRepository.save(serUser);

		return "User Registered Successfully!";
	}

	//Using UsernamePasswordAuthenticationToken
	@Override
	public String login(LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginDto.usernameOrEmail(),
						loginDto.password()
				)
		);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = "ssucces tests";

        return token;
	}

}
