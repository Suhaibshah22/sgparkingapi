package com.syedsuhaibshah.sgparkingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bedatadriven.jackson.datatype.jts.JtsModule;

@SpringBootApplication
public class SgparkingapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgparkingapiApplication.class, args);
	}

	// Inject our global jts dependency.
	@Bean
	public JtsModule jtsModule() {
		return new JtsModule();
	}
}
