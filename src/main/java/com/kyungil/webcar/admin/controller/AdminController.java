package com.kyungil.webcar.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kyungil.webcar.admin.domain.User;
import com.kyungil.webcar.admin.service.AdminService;
import com.kyungil.webcar.img.service.ImgService;
import com.kyungil.webcar.product.domain.Car;
import com.kyungil.webcar.product.service.CarService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private CarService carService;
	@Autowired
	private ImgService imgService;

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
    @PostMapping("/product/add")
    public String addCar(@RequestParam("image") MultipartFile imageFile, @RequestParam Map<String, String> data) {

        int imgId = imgService.addImgAndGetId(imageFile);
        int brand = Integer.parseInt(data.get("brand"));
        int carType = Integer.parseInt(data.get("carType"));
        int price = Integer.parseInt(data.get("price"));

        carService.addCar(new Car(data.get("title"),
                data.get("content"),
                price,
                imgId,
                brand,
                carType));

        return "redirect:/admin/notice";
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
	public String getUserListPage(Model model, @RequestParam(defaultValue = "1", name = "page") int page,
			@RequestParam(defaultValue = "5", name = "pageSize") int pageSize) {
		List<User> pageUsers = adminService.getUsersByPage(page, pageSize);

		int totalBoards = adminService.getPageCount();
		int totalPages = (int) Math.ceil((double) totalBoards / pageSize);
		model.addAttribute("users", pageUsers);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("title", "회원조회페이지");
		model.addAttribute("path", "/admin/userlist");
		model.addAttribute("content", "userlistFragment");
		model.addAttribute("contentHead", "userlistFragmentHead");
		return "admin/adminlayout";
	}

	@PostMapping("/user/delete/{userId}")
	public String deleteUser(@PathVariable(name = "userId") int userId, HttpSession session) {
		String userRoleString = (String) session.getAttribute("userRole");

		if (userRoleString != null) {
			User.Role userRole = User.Role.valueOf(userRoleString);

			if (User.Role.ADMIN.equals(userRole)) {
				adminService.delete(userId);
				return "redirect:/admin/user/list";
			} else {
				System.out.println("권한없음");
			}
		}

		return "redirect:/";
	}

	@PostMapping("/user/addadmin")
	public String addAdmin(@RequestParam("selectedUsers") int[] selectedUsers, HttpSession session) {
		String userRoleString = (String) session.getAttribute("userRole");
		if (userRoleString != null) {
			User.Role userRole = User.Role.valueOf(userRoleString);

			if (User.Role.ADMIN.equals(userRole)) {
				adminService.addAdmin(selectedUsers);
				return "redirect:/admin/user/list";
			} else {
				System.out.println("권한없음");
			}
		}
		return "redirect:/";

	}

	@GetMapping("/user/detail/{userId}")
	public String getUserDetailPage(@PathVariable(name = "userId") int userId, Model model, HttpSession session) {
		String userRoleString = (String) session.getAttribute("userRole");
		if (userRoleString != null) {
			User.Role userRole = User.Role.valueOf(userRoleString);
			if (User.Role.ADMIN.equals(userRole)) {
				User user = adminService.get(userId);
				model.addAttribute("user", user);
				model.addAttribute("title", "회원상세보기페이지");
				model.addAttribute("path", "/admin/userdetail");
				model.addAttribute("content", "userdetailFragment");
				model.addAttribute("contentHead", "userdetailFragmentHead");
				return "admin/adminlayout";
			}
		}
		return "redirect:/";
	}
}