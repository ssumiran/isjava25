package j.jav25.security.config;


import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {
	
	@Bean   //This is custom security chain
	public SecurityFilterChain secFilterChain(HttpSecurity http) {
		
		/*
		//To make STATELESS every time it will get new SESSION ID
		http.sessionManagement(s -> s.sessionCreationPolicy(
									SessionCreationPolicy.STATELESS));
		*/
		//Doing httpSec chain
//		return http.sessionManagement(s -> s.sessionCreationPolicy(
//									SessionCreationPolicy.STATELESS))
//				.build();
		
			
		http.authorizeHttpRequests(					//To enable each Request need to authenticated
			(requests) -> requests
				//.anyRequest().authenticated()  //Secure all URIs
				//.anyRequest().denyAll()       // Deny all URIs
				//.anyRequest().permitAll()      // Allow all URIs
			
			//.requestMatchers(HttpMethod.GET, "/security/user","/security/admin").authenticated()  //Secure 2 urls
			//.requestMatchers("/security/welcome").permitAll()     //Allow welcome url
			
//Using Roles hasRole, hasAnyRole
			//	.requestMatchers("/security/user").hasAnyRole("USER","ADMIN")
			//	.requestMatchers("/security/admin").hasRole("ADMIN")
				
				.requestMatchers("/security/welcome").permitAll()
				.anyRequest().authenticated()
		);        	
		
		//http.formLogin(withDefaults());   //Activate default Form Login for Browser
		http.formLogin(form -> form.disable());   //Disable Form Login for Browser
		
		http.httpBasic(withDefaults());  //Activate default Form Login for PostMan
		
		//Disable Cross-Site Request Forgery
		http.csrf(csrf -> csrf.disable());
				
		return http.build();
	}
	
	@Bean   //PasswordEncoder
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Create more Users using UserDetailService in Memory. This bean is hardcoded users
	@Bean   
	public UserDetailsService userDetailsService() {
		
		UserDetails user1 = User.builder()
							.username("user")
							.password(passwordEncoder().encode("user123"))  //PasswordEncoder
							.roles("USER")
							.build();
		
		UserDetails user2 = User.builder()
							.username("admin")
							.password(passwordEncoder().encode("admin123")) //PasswordEncoder
							.roles("ADMIN")
							.build();
		
		return new InMemoryUserDetailsManager(user1,user2);
	}
	
		
}
