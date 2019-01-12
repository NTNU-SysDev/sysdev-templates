package no.ntnu.sysdev.SpringBoot_Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is our main class for running the application
 *
 * the @SpringBootApplication annotation above the class
 * Indicates a configuration class that declares one or
 * more @Bean methods and also triggers auto-configuration and
 * component scanning.
 * This is a convenience annotation that is equivalent to
 * declaring @Configuration, @EnableAutoConfiguration and @ComponentScan.
 */
@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

}

