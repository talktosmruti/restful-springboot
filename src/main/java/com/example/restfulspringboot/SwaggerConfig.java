package com.example.restfulspringboot;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	static String[] types= {"application/json","application/xml"};
	
	public Set<String> typs = new HashSet<>(Arrays.asList(types));
	

	
	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(
						new ApiInfo(
								"Smruti's sample web services", 
								"Demo to play with all RESTFul features", 
								"1.0.0", 
								"NA", 
								"8622908520", "NA", "NA"))
				.produces(typs)
				.consumes(typs)
				;
	}

}
