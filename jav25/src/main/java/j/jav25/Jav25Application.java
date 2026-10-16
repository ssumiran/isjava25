package j.jav25;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Jav25Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Jav25Application.class, args);
	}

	
	//Create ModelMapper bean for DTO to Entity convertion
	@Bean   
	public ModelMapper modelMapper(){ 
		return new ModelMapper(); 
	}
}
