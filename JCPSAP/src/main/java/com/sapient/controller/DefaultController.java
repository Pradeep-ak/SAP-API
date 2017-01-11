package com.sapient.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

	@RequestMapping("/")
    @ResponseBody
	public String index() {
		return "Welcome to JCP Sapient API";
	}
}
