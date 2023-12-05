package com.kyungil.webcar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test {

	@GetMapping("/")
	public String test() {

		return "basic/layout";
	}
}
