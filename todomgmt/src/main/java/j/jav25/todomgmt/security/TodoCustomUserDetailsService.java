package j.jav25.todomgmt.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import j.jav25.todomgmt.entity.TodoUser;
import j.jav25.todomgmt.repository.TodoUserRepository;

@Service
public class TodoCustomUserDetailsService  implements UserDetailsService {
//Create Custom User Details
	
	private TodoUserRepository todoUserRepository;
	
	//Constructor Injection
	public TodoCustomUserDetailsService (TodoUserRepository todoUserRepository) {
		this.todoUserRepository = todoUserRepository;
	}
	@Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		TodoUser todoUser = todoUserRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
	            .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));
	
	    Set<GrantedAuthority> authorities = todoUser.getTodoRoles().stream()
	    		.map( (role) -> new SimpleGrantedAuthority(role.getName()))	            
	            .collect(Collectors.toSet());
	
	    return new User(
	            usernameOrEmail,
	            todoUser.getPassword(),
	            authorities
	    );
	}
}
