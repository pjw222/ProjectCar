package com.kyungil.webcar.notice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kyungil.webcar.notice.domain.Notice;
import com.kyungil.webcar.notice.service.NoticeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	@GetMapping("/notice")
	public String getNotice(Model model, @RequestParam(defaultValue = "1", name = "page") int page,
			@RequestParam(defaultValue = "5", name = "pageSize") int pageSize) {

		List<Notice> pagedNotices = noticeService.getNoticesByPage(page, pageSize);

		int totalBoards = noticeService.getPageCount();
		int totalPages = (int) Math.ceil((double) totalBoards / pageSize);
		model.addAttribute("notices", pagedNotices);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("title", "공지사항");
		model.addAttribute("path", "/notice/notice");
		model.addAttribute("content", "noticeFragment");
		model.addAttribute("contentHead", "noticeFragmentHead");
		model.addAttribute("pageType", "notice");
		return "basic/layout";
	}



	@GetMapping("/notice/view/{noticeId}")
	public String showBoardDetail(@PathVariable("noticeId") int noticeId, Model model) {
		Notice notice = noticeService.get(noticeId);

		model.addAttribute("notices", notice);
		model.addAttribute("title", "공지사항상세보기");
		model.addAttribute("path", "/notice/view");
		notice.setContent(notice.getContent().replace("\n", "<br />"));
		model.addAttribute("content", "viewFragment");
		model.addAttribute("contentHead", "viewFragmentHead");
		model.addAttribute("pageType", "notice");
		return "basic/layout";
	}

}
