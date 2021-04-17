package com.stackroute.userservice;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EntityScan("com.stackroute.user.model")
@ComponentScan("com.stackroute.*")
@EnableJpaRepositories(basePackages="com.stackroute.user.dao")
public class UserServiceApplication {

    
    
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(UserServiceApplication.class, args);
		
	}
	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/user/**"))
				.apis(RequestHandlerSelectors.basePackage("com.stackroute"))
				.build()
				.apiInfo(getApiDetails());
	}
	
	private ApiInfo getApiDetails() {
		return new ApiInfo("User API",
				"Api for user registration and validation",
				"1.0", "Api for case study", new Contact("Sumanta Poria", "IBM", "ibm.com"), "Stackroute", "Stackroute", Collections.emptyList());
	}	
    

}

