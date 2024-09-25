package com.example.EducationClassProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EducationClassProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducationClassProjectApplication.class, args);
	}

}
