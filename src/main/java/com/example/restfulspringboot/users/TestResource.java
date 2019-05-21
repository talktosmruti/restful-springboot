package com.example.restfulspringboot.users;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestResource {
	
	@Autowired
	ResourceBundleMessageSource msgSource;

	@GetMapping
	public String test(@RequestHeader(name="Accept-Language", required = false) Locale locale) {
		return msgSource.getMessage("love.text",null, locale);
	}
}
