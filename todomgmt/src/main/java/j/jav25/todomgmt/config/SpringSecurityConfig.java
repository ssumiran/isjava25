package j.jav25.todomgmt.config;


import static org.springframework.security.config.Customizer.withDefaults;

import j.jav25.todomgmt.security.TodoCustomUserDetailsService;
import j.jav25.todomgmt.security.TodoJwtAuthenticationEntryPoint;
import j.jav25.todomgmt.security.TodoJwtAuthenticationFilter;

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


@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

    private TodoCustomUserDetailsService todoCustomUserDetailsService;
    private TodoJwtAuthenticationEntryPoint todoJwtAuthenticationEntryPoint;
    private TodoJwtAuthenticationFilter todoJwtAuthenticationFilter;

    SpringSecurityConfig(TodoCustomUserDetailsService todoCustomUserDetailsService,
    				TodoJwtAuthenticationEntryPoint todoJwtAuthenticationEntryPoint,
    				TodoJwtAuthenticationFilter todoJwtAuthenticationFilter) {
        this.todoCustomUserDetailsService = todoCustomUserDetailsService;
        this.todoJwtAuthenticationEntryPoint = todoJwtAuthenticationEntryPoint;
		this.todoJwtAuthenticationFilter = todoJwtAuthenticationFilter;
    }
	
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
	SecurityFilterChain secFilterChain(HttpSecurity http) throws Exception {
		
			
		http.authorizeHttpRequests(					//To enable each Request need to authenticated
			(requests) -> requests
				//.anyRequest().authenticated()  //Secure all URIs
				//.anyRequest().denyAll()       // Deny all URIs
				//.anyRequest().permitAll()      // Allow all URIs
			
//Using Roles hasRole, hasAnyRole
				//.requestMatchers(HttpMethod.GET, "/api/todos").hasAnyRole("USER","ADMIN")
				//.requestMatchers(HttpMethod.POST, "/api/todos").hasRole("ADMIN")
				
				.requestMatchers("/api/auth/**").permitAll()
				.anyRequest().authenticated()
		);        	
		
		//http.formLogin(withDefaults());   //Activate default Form Login for Browser
		http.formLogin(form -> form.disable());   //Disable Form Login for Browser
		
		http.httpBasic(withDefaults()  //Activate default Form Login for PostMan
				
			//Using JWT Custom Exception AuthenticationEntryPoint	
			//jwtAuth -> jwtAuth.authenticationEntryPoint(new TodoJwtAuthenticationEntryPoint())	
		); 
				
		//Using Exception to handle TodoJwtAuthenticationEntryPoint()	
		http.exceptionHandling( ex -> ex.authenticationEntryPoint(new TodoJwtAuthenticationEntryPoint()) );
		
		//Add Filter Before meaning to execute this before other DAO user-password filter
		http.addFilterBefore(todoJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

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
