package com.example.restfulspringboot.versioing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/versioning")
public class VersioningResources {
	
	@GetMapping(value = "/requestparam", params="version=1")
	public String requestParamVersioning1() {
		return "v1";
		
	}
	
	@GetMapping(value = "/requestparam", params="version=2")
	public String requestParamVersioning2() {
		return "v2";
		
	}
	
	@GetMapping(value = "/headerparam", headers="X-API-VERSION=1")
	public String headerParamVersioning1() {
		return "v1";
		
	}
	
	@GetMapping(value = "/headerparam", headers="X-API-VERSION=2")
	public String headerParamVersioning2() {
		return "v2";
		
	}
	
	@GetMapping(value = "/v1/uri")
	public String uriv1() {
		return "v1";
		
	}
	
	@GetMapping(value = "/v2/uri")
	public String uriv2() {
		return "v2";
		
	}
	
	@GetMapping(value = "/produces", produces="application/vnd.company.app-v1+json")
	public String producesv1() {
		return "v1";
		
	}
	
	@GetMapping(value = "/produces",produces="application/vnd.company.app-v2+json")
	public String producesv2() {
		return "v2";
		
	}
	
	

}
