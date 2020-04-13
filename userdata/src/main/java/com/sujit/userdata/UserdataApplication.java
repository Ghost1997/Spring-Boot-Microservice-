package com.sujit.userdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UserdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserdataApplication.class, args);
	}

}
