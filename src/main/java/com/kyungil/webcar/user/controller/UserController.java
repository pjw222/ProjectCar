package com.kyungil.webcar.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping("/regist")
	public String getRegist(Model model) {
		model.addAttribute("title", "게시판");
		model.addAttribute("imgPath", "/user/registImg");
		model.addAttribute("imgContent", "registImgFragment");
		model.addAttribute("imgContentHead", "registImgFragmentHead");
		model.addAttribute("path", "/user/regist");
		model.addAttribute("content", "registFragment");
		model.addAttribute("contentHead", "registFragmentHead");
		
		return "basic/layout";
	}
}
