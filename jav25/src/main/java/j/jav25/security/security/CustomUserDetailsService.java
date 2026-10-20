package j.jav25.security.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import j.jav25.security.entity.SecRole;
import j.jav25.security.entity.SecUser;
import j.jav25.security.repository.SecUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private SecUserRepository secUserRepository;

    public CustomUserDetailsService(SecUserRepository secUserRepository) {
        this.secUserRepository = secUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        SecUser secUser = secUserRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail)
                );

        Set<SecRole> secRoles = secUser.getSecRoles();

        Set<GrantedAuthority> authorities = secRoles.stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new User(usernameOrEmail,  secUser.getPassword(), authorities);
    }
}
