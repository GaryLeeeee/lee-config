package com.garylee.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class LeeConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeeConfigApplication.class, args);
	}

}
