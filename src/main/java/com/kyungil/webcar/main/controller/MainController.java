package com.kyungil.webcar.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String main(Model model) {
		
		model.addAttribute("title", "게시판");
		model.addAttribute("imgPath", "/basic/img");
		model.addAttribute("imgContent", "imgFragment");
		model.addAttribute("imgContentHead", "imgFragmentHead");
		model.addAttribute("path", "/main/main");
		model.addAttribute("content", "mainFragment");
		model.addAttribute("contentHead", "mainFragmentHead");
				
		return "basic/layout";
	}
}
