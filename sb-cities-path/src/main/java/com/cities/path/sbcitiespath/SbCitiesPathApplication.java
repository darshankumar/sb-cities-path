package com.cities.path.sbcitiespath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.annotations.Api;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author Darshan Bhatia
 *
 */
@SpringBootApplication
@EnableSwagger2
@Api(value = "Spring Boot Cities Connection Path", description = "Spring Boot Cities Connection Path")
public class SbCitiesPathApplication {

	@Autowired
	private PathUtil pathUtil;

	public static void main(String[] args) {
		SpringApplication.run(SbCitiesPathApplication.class, args);
	}

	@Bean
	CommandLineRunner getCommandLineRunner() {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				pathUtil.loadConutryMapFromFile(); // starting map as application start
			}
		};
	}
}
