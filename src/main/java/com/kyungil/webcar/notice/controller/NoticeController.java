package com.kyungil.webcar.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeController {
	
	@GetMapping("/notice")
	public String getNotice(Model model) {
		
		model.addAttribute("title", "공지사항");
		model.addAttribute("path", "/notice/notice");
		model.addAttribute("content", "noticeFragment");
		model.addAttribute("contentHead", "noticeFragmentHead");
		model.addAttribute("pageType", "notice");
		return "basic/layout";
	}
}
