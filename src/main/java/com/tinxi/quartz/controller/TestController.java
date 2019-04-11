package com.tinxi.quartz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Dsecription
 * @Auth tinxi
 * @DATAE 2019/4/11 12:11
 */
@RestController
public class TestController {

	@GetMapping("/getTest")
	public String getTest() {

		return "success";
	}


}
