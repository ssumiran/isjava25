package j.jav25.security.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import j.jav25.security.dto.LoginDto;
import j.jav25.security.dto.RegisterDto;
import j.jav25.security.entity.SecRole;
import j.jav25.security.entity.SecUser;
import j.jav25.security.repository.SecRoleRepository;
import j.jav25.security.repository.SecUserRepository;
import j.jav25.security.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{

    private PasswordEncoder passwordEncoder;
	private SecUserRepository secUserRepository;
	private SecRoleRepository secRoleRepository;
	
	public AuthServiceImpl (PasswordEncoder passwordEncoder,
			SecUserRepository secUserRepository,  SecRoleRepository secRoleRepository) {
		this.passwordEncoder = passwordEncoder;
		this.secUserRepository= secUserRepository;
		this.secRoleRepository = secRoleRepository;
		
	}
	
	@Override
	public String register(RegisterDto registerDto) {
		
		if(secUserRepository.existsByUsername(registerDto.username())){
		    throw new UsernameNotFoundException("Username already exists.");
		}

        SecUser serUser = new SecUser();
        serUser.setName(registerDto.name());
        serUser.setUsername(registerDto.username());
        serUser.setEmail(registerDto.email());
        serUser.setPassword(passwordEncoder.encode(registerDto.password()));

        Set<SecRole> secRoles = new HashSet<>();
        for (String r : registerDto.roles()){
        	secRoles.add(secRoleRepository.findByName(r));
        }

        serUser.setSecRoles(secRoles);

        secUserRepository.save(serUser);

        return "User Registered Successfully!";
	}


}
