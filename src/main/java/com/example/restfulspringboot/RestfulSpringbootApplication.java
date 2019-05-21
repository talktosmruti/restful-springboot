package com.example.restfulspringboot;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class RestfulSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulSpringbootApplication.class, args);
	}

	@Bean
	public LocaleResolver getLocalResolver() {
		SessionLocaleResolver resolv = new SessionLocaleResolver();
		resolv.setDefaultLocale(Locale.US);
		return resolv;
	}

	@Bean
	public ResourceBundleMessageSource getMessageSource() {
		ResourceBundleMessageSource msg = new ResourceBundleMessageSource();
		msg.setBasename("message");
		return msg;
	}

}
