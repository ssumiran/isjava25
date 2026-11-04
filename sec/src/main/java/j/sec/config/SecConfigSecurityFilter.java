package j.sec.config;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

import j.sec.exception.CustomAccessDeniedHandler;
import j.sec.exception.CustomBasicAuthenticationEntryPoint;

@Configuration
public class SecConfigSecurityFilter {

	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());*/
        /*http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());*/
        http.authorizeHttpRequests( 
        	(requests) -> requests
                .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                .requestMatchers("/notices", "/contact", "/error","/register").permitAll()
        );
        
        //Force all API using only HTTPS
        //http.redirectToHttps((https) -> https.requestMatchers(AnyRequestMatcher.INSTANCE)); 
        
        http.formLogin(withDefaults());
        //http.httpBasic(withDefaults());
        
        //Custom Basic AuthenticationEntryPoint Exception handler
        http.httpBasic(cba -> cba.authenticationEntryPoint( new CustomBasicAuthenticationEntryPoint()));
        
        //Custom Access Denied Exception handler
        http.exceptionHandling (cade -> cade.accessDeniedHandler( new CustomAccessDeniedHandler()));
        
        http.csrf(csrf -> csrf.disable());
        
        return http.build();
    }
	
	//In memory Users   InMemoryUserDetailsManager(user, admin);
/*	@Bean
    UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user").password("{noop}user").authorities("read").build();
        UserDetails admin = User.withUsername("admin") //54321
                            .password("{bcrypt}$2a$12$88.f6upbBvy0okEa7OfHFuorV29qeK.sVbB9VQ6J6dWM1bW6Qef8m")
                            .authorities("admin").build();
        return new InMemoryUserDetailsManager(user, admin);
    }
*/	
	//Using Database Users   JdbcUserDetailsManager(dataSource)
	//Using JdbcUserDetailsManager has pre defined schema structure tables
	//User table and Authorities table
/*	@Bean
	UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
	}    
*/
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //To check if the Password is too simple or compromised by Hacker
    /*@Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }*/
	
}
