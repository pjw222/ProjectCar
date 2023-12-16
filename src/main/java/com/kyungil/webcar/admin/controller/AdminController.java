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
import com.kyungil.webcar.notice.domain.Notice;
import com.kyungil.webcar.notice.service.NoticeService;
import com.kyungil.webcar.product.domain.Car;
import com.kyungil.webcar.product.service.CarService;
import com.kyungil.webcar.reservation.domain.Reservation;
import com.kyungil.webcar.reservation.service.ReservationService;

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
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private NoticeService noticeService;
	@GetMapping("/notice")
	public String getAdminPage(Model model, @RequestParam(defaultValue = "1", name = "page") int page,
			@RequestParam(defaultValue = "5", name = "pageSize") int pageSize) {
		List<Notice> pagedNotices = noticeService.getNoticesByPage(page, pageSize);

		int totalBoards = noticeService.getPageCount();
		int totalPages = (int) Math.ceil((double) totalBoards / pageSize);
		model.addAttribute("notices", pagedNotices);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("title", "관리자페이지");
		model.addAttribute("path", "/admin/notice");
		model.addAttribute("content", "noticeFragment");
		model.addAttribute("contentHead", "noticeFragmentHead");
		return "admin/adminlayout";
	}

	@PostMapping("/notice/add")
	public String boardAdd(@RequestParam Map<String, String> data, HttpSession session) {
		try {
			int userId = (Integer) session.getAttribute("userId");

			noticeService.add(new Notice(data.get("title"), data.get("content"), userId));

			return "redirect:/admin/notice";

		} catch (Exception e) {
			return "redirect:/";
		}
	}
	@GetMapping("/notice/add")
	public String getNoticeAddPage(Model model, HttpSession session) {
		String userRoleString = (String) session.getAttribute("userRole");

		if (userRoleString != null) {
			User.Role userRole = User.Role.valueOf(userRoleString);

			if (User.Role.ADMIN.equals(userRole)) {
				model.addAttribute("title", "공지사항작성페이지");
				model.addAttribute("path", "/admin/addnotice");
				model.addAttribute("content", "addnoticeFragment");
				model.addAttribute("contentHead", "addnoticeFragmentHead");
				return "admin/adminlayout";
			} else {
				System.out.println("권한없음");
			}
		}
		return "redirect:/";
	}

	@GetMapping("/product/add")
	public String getProductAddPage(Model model, HttpSession session) {
		String userRoleString = (String) session.getAttribute("userRole");

		if (userRoleString != null) {
			User.Role userRole = User.Role.valueOf(userRoleString);

			if (User.Role.ADMIN.equals(userRole)) {
				model.addAttribute("title", "상품추가페이지");
				model.addAttribute("path", "/admin/addproduct");
				model.addAttribute("content", "addproductFragment");
				model.addAttribute("contentHead", "addproductFragmentHead");
				return "admin/adminlayout";
			} else {
				System.out.println("권한없음");
			}
		}
		return "redirect:/";
	}

	@PostMapping("/product/add")
	public String addCar(@RequestParam("image") MultipartFile imageFile, @RequestParam Map<String, String> data,
			HttpSession session) {
		String userRoleString = (String) session.getAttribute("userRole");

		if (userRoleString != null) {
			User.Role userRole = User.Role.valueOf(userRoleString);

			if (User.Role.ADMIN.equals(userRole)) {
				int imgId = imgService.addImgAndGetId(imageFile);
				int brand = Integer.parseInt(data.get("brand"));
				int carType = Integer.parseInt(data.get("carType"));
				int price = Integer.parseInt(data.get("price"));

				carService.addCar(new Car(data.get("title"), data.get("content"), price, imgId, brand, carType));

				return "redirect:/admin/notice";
			} else {
				System.out.println("권한없음");
			}
		}
		return "redirect:/";
	}

	@GetMapping("/product/list")
	public String getProductListPage(Model model, @RequestParam(defaultValue = "1", name = "page") int page,
			@RequestParam(defaultValue = "5", name = "pageSize") int pageSize, HttpSession session) {
		String userRoleString = (String) session.getAttribute("userRole");

		if (userRoleString != null) {
			User.Role userRole = User.Role.valueOf(userRoleString);

			if (User.Role.ADMIN.equals(userRole)) {
				List<Reservation> reservations = reservationService.getReservationByPage(page, pageSize);
				int totalBoards = reservationService.getPageCount();
				int totalPages = (int) Math.ceil((double) totalBoards / pageSize);
				model.addAttribute("reservations", reservations);
				model.addAttribute("currentPage", page);
				model.addAttribute("totalPages", totalPages);
				model.addAttribute("title", "예약상품조회페이지");
				model.addAttribute("path", "/admin/productlist");
				model.addAttribute("content", "productlistFragment");
				model.addAttribute("contentHead", "productlistFragmentHead");
				return "admin/adminlayout";
			} else {
				System.out.println("권한없음");
			}
		}
		return "redirect:/";
	}

	@PostMapping("/product/delivery")
	public String delivery(@RequestParam("selectedUsers") int[] selectedUsers, HttpSession session) {
		String userRoleString = (String) session.getAttribute("userRole");

		if (userRoleString != null) {
			User.Role userRole = User.Role.valueOf(userRoleString);

			if (User.Role.ADMIN.equals(userRole)) {
				reservationService.delivery(selectedUsers);
				return "redirect:/admin/product/list";
			} else {
				System.out.println("권한없음");
			}
		}
		return "redirect:/";
	}

	@GetMapping("/user/list")
	public String getUserListPage(Model model, @RequestParam(defaultValue = "1", name = "page") int page,
			@RequestParam(defaultValue = "5", name = "pageSize") int pageSize, HttpSession session) {
		String userRoleString = (String) session.getAttribute("userRole");

		if (userRoleString != null) {
			User.Role userRole = User.Role.valueOf(userRoleString);

			if (User.Role.ADMIN.equals(userRole)) {
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
			} else {
				System.out.println("권한없음");
			}
		}
		return "redirect:/";
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
				List<Reservation> reservations = reservationService.getReservation(userId);
				model.addAttribute("user", user);
				model.addAttribute("reservations", reservations);
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