package com.kyungil.webcar.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kyungil.webcar.user.domain.User;
import com.kyungil.webcar.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/regist")
	public String getRegist(Model model) {
		model.addAttribute("title", "회원가입");
		model.addAttribute("imgPath", "/user/registImg");
		model.addAttribute("imgContent", "registImgFragment");
		model.addAttribute("imgContentHead", "registImgFragmentHead");
		model.addAttribute("path", "/user/regist");
		model.addAttribute("content", "registFragment");
		model.addAttribute("contentHead", "registFragmentHead");

		return "basic/layout";
	}

	@PostMapping("/regist")
	public String registPost(@RequestParam Map<String, String> map, Model model) {
		System.out.println("요청 받음");
		try {
			User tempUser = new User(map.get("userId"), map.get("password"), map.get("name"), map.get("phone"),
					map.get("address"));
			if (map.get("gender") != null) {
				tempUser.setGender(Integer.parseInt(map.get("gender")));
			}
			userService.registerUser(tempUser);
		
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
	}
	@PostMapping("/login")
	public String loginPost(@RequestParam Map<String, String> map, HttpSession session) {
		User tempUser = new User();
		tempUser.setUserId(map.get("userId"));
		tempUser.setPassword(map.get("password"));
		tempUser = userService.login(tempUser);

		if (tempUser != null) {
			session.setAttribute("userName", tempUser.getName());
			session.setAttribute("userId", tempUser.getId());
			session.setAttribute("userGender", tempUser.getGenderAsString());
			session.setAttribute("userAddress", tempUser.getAddress());
			session.setAttribute("userPhone", tempUser.getPhone());
		}
		return "redirect:/";
	}
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/mypage")
	public String getMyPage(Model model, HttpSession session) {
	    model.addAttribute("title", "마이페이지");
	    model.addAttribute("path", "/user/myPage");
	    model.addAttribute("content", "myPageFragment");
	    model.addAttribute("contentHead", "myPageFragmentHead");
	    return "basic/layout";
	}
	@PostMapping("/mypage/delete")
	public String deleteUser(HttpSession session) {
	    int userId =(int) session.getAttribute("userId");
	    userService.delete(userId);
	    session.invalidate();
	    return "redirect:/";
	}

}
