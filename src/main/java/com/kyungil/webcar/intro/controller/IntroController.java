package com.kyungil.webcar.intro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IntroController {
	@GetMapping("/intro")
	public String getRegist(Model model) {
		model.addAttribute("title", "게시판");
		model.addAttribute("path", "/intro/intro");
		model.addAttribute("content", "introFragment");
		model.addAttribute("contentHead", "introFragmentHead");
		 model.addAttribute("pageType", "intro");
		return "basic/layout";
	}
}

