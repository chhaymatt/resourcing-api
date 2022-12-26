package com.matthewchhay.resourcingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ResourcingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourcingApiApplication.class, args);
	}

}
