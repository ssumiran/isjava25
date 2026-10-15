package j.jav25;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Jav25Application {
	
	/*
	 * import org.modelmapper.ModelMapper;
	 * 
	 * @Bean public ModelMapper modelMapper(){ return new ModelMapper(); }
	 
	 *
	 *<dependency>
            <groupId>org.modelmapper</groupId>
            <artifactId>modelmapper</artifactId>
            <version>3.2.5</version>
        </dependency>
	 */

	public static void main(String[] args) {
		SpringApplication.run(Jav25Application.class, args);
	}

}
