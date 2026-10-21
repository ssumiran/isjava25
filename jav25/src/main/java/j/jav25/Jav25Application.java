package j.jav25;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Jav25Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Jav25Application.class, args);
		
		/*//Encode text password to   $2a$10$xsdHfsD6HJDXR1Q7s30qBOEn.iZv9gN.yajUbxQ1IFoEK2yba6QZ2
		BCryptPasswordEncoder pEncoder = new BCryptPasswordEncoder();
		System.out.println(pEncoder.encode("userone123"));
		System.out.println(pEncoder.encode("usertwo123"));
		System.out.println(pEncoder.encode("admin123"));
		*/
	}

	
	//Create ModelMapper bean for DTO to Entity convertion
	@Bean   
	ModelMapper modelMapper(){ 
		return new ModelMapper(); 
	}
	
	//To encode password utility
	
}
