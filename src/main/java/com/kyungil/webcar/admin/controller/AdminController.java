package com.kyungil.webcar.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@GetMapping("/notice")
	public String getAdminPage(Model model) {
		
		model.addAttribute("title", "관리자페이지");
		model.addAttribute("path", "/admin/notice");
		model.addAttribute("content", "noticeFragment");
		model.addAttribute("contentHead", "noticeFragmentHead");
		return "admin/adminlayout";		
	}
	@GetMapping("/notice/add")
	public String getNoticeAddPage(Model model) {
		model.addAttribute("title", "공지사항작성페이지");
		model.addAttribute("path", "/admin/addnotice");
		model.addAttribute("content", "addnoticeFragment");
		model.addAttribute("contentHead", "addnoticeFragmentHead");
		return "admin/adminlayout";
	}
	@GetMapping("/product/add")
	public String getProductAddPage(Model model) {
		model.addAttribute("title", "상품추가페이지");
		model.addAttribute("path", "/admin/addproduct");
		model.addAttribute("content", "addproductFragment");
		model.addAttribute("contentHead", "addproductFragmentHead");
		return "admin/adminlayout";
	}
	@GetMapping("/product/list")
	public String getProductListPage(Model model) {
		model.addAttribute("title", "예약상품조회페이지");
		model.addAttribute("path", "/admin/productlist");
		model.addAttribute("content", "productlistFragment");
		model.addAttribute("contentHead", "productlistFragmentHead");
		return "admin/adminlayout";
	}
	@GetMapping("/user/list")
	public String getUserListPage(Model model) {
		model.addAttribute("title", "회원조회페이지");
		model.addAttribute("path", "/admin/userlist");
		model.addAttribute("content", "userlistFragment");
		model.addAttribute("contentHead", "userlistFragmentHead");
		return "admin/adminlayout";
	}
	@GetMapping("/user/detail")
	public String getUserDetailPage(Model model) {
		model.addAttribute("title", "회원상세보기페이지");
		model.addAttribute("path", "/admin/userdetail");
		model.addAttribute("content", "userdetailFragment");
		model.addAttribute("contentHead", "userdetailFragmentHead");
		return "admin/adminlayout";
	}
}