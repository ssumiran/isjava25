package j.jav25.todomgmt.config;


import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*import j.jav25.security.exception.CustomAccessDeniedHandler;
import j.jav25.security.exception.CustomAuthenticationEntryPoint;
import j.jav25.security.security.JwtAuthenticationEntryPoint;
import j.jav25.security.security.JwtAuthenticationFilter;*/


@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {
	
	/*
	 * private UserDetailsService userDetailsService; //private
	 * JwtAuthenticationFilter jwtAuthenticationFilter;
	 * 
	 * public SpringSecurityConfig(UserDetailsService userDetailsService
	 * //JwtAuthenticationFilter jwtAuthenticationFilter ) { this.userDetailsService
	 * = userDetailsService; //this.jwtAuthenticationFilter =
	 * jwtAuthenticationFilter; }
	 */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        return configuration.getAuthenticationManager();
    }
	
	@Bean   //This is custom security chain
	SecurityFilterChain secFilterChain(HttpSecurity http) {
		
			
		http.authorizeHttpRequests(					//To enable each Request need to authenticated
			(requests) -> requests
				//.anyRequest().authenticated()  //Secure all URIs
				//.anyRequest().denyAll()       // Deny all URIs
				//.anyRequest().permitAll()      // Allow all URIs
			
//Using Roles hasRole, hasAnyRole
				.requestMatchers(HttpMethod.GET, "/api/todos").hasAnyRole("USER","ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/todos").hasRole("ADMIN")
				
				//.requestMatchers("/api/todos/**").permitAll()
				.anyRequest().authenticated()
		);        	
		
		//http.formLogin(withDefaults());   //Activate default Form Login for Browser
		http.formLogin(form -> form.disable());   //Disable Form Login for Browser
		
		http.httpBasic(withDefaults()  //Activate default Form Login for PostMan
				
			//Using Custom Exception AuthenticationEntryPoint	
			//basicAuth -> basicAuth.authenticationEntryPoint(new CustomAuthenticationEntryPoint())	
			
			//Using JWT Custom Exception AuthenticationEntryPoint	
			//jwtAuth -> jwtAuth.authenticationEntryPoint(new JwtAuthenticationEntryPoint())	
		); 
		
		//Add Filter Before meaning to execute this before other DAO user-password filter
		//http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		//Using Custom Exception to handle AccessDenielHandler()	
		//http.exceptionHandling(ex -> ex.accessDeniedHandler(new CustomAccessDeniedHandler()));
		
		//Disable Cross-Site Request Forgery
		http.csrf(csrf -> csrf.disable());
				
		return http.build();
	}
	
	@Bean   //PasswordEncoder
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Create more Users using UserDetailService in Memory. This bean is hardcoded users
/*	@Bean   
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
*/	
		
}
