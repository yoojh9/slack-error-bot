package com.slack.logback.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	private Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping(value = "/api/v1/test1", method = RequestMethod.GET)
	public String responseOk() {
		return "ok";
	}

	@RequestMapping(value = "/api/v1/test2", method = RequestMethod.GET)
	public String responseFail(@RequestParam(required = false) String value) {
		if (value == null || value.equals("")) {
			throw new IllegalArgumentException("value is null");
		}
		return "fail";
	}

	@RequestMapping(value = "/api/v1/test3", method = RequestMethod.POST)
	public String responseFail2(
			@RequestParam(required = false) String value1,
			@RequestParam(required = false) String value2,
			@RequestParam(required = false) String value3,
			@RequestParam(required = false) String value4) {
		if (value1 == null || value1.equals("")) {
			throw new IllegalArgumentException("value is null");
		}
		return "fail";
	}

}
