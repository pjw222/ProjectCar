package com.kyungil.webcar.brand.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrandController {

	@GetMapping("/brand")
	public String getBrand(Model model) {
		
		model.addAttribute("title", "브랜드목록");
		model.addAttribute("path", "/brand/brand");
		model.addAttribute("content", "brandFragment");
		model.addAttribute("contentHead", "brandFragmentHead");
		
		return "basic/layout";
	}
}
