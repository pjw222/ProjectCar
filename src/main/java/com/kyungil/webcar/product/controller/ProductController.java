package com.kyungil.webcar.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kyungil.webcar.likes.service.LikesService;
import com.kyungil.webcar.notice.domain.Notice;
import com.kyungil.webcar.product.domain.Car;
import com.kyungil.webcar.product.service.CarService;


@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private CarService carService;
	@Autowired
	private LikesService likesService;
	
	@GetMapping("/brand/hyundai")
	public String getBrandHyundaiPage(Model model, @RequestParam(defaultValue = "1", name = "page") int page,
			@RequestParam(defaultValue = "8", name = "pageSize") int pageSize) {

		List<Car> car = carService.getCarListByPage(1,page, pageSize);

		int totalCar = carService.getPageCount(1);
		int totalPages = (int) Math.ceil((double) totalCar / pageSize);
		model.addAttribute("carlist", car);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("title", "현대");
		model.addAttribute("path", "/product/brand/hyundai");
		model.addAttribute("content", "hyundaiFragment");
		model.addAttribute("contentHead", "brandFragmentHead");
		model.addAttribute("pageType", "/product/brand");
		model.addAttribute("brandType", "/product/brand/hyundai");
		
		return "basic/layout";
	}
	@GetMapping("/brand/kia")
	public String getBrandKiaPage(Model model, @RequestParam(defaultValue = "1", name = "page") int page,
			@RequestParam(defaultValue = "8", name = "pageSize") int pageSize) {

		List<Car> car = carService.getCarListByPage(2,page, pageSize);

		int totalCar = carService.getPageCount(2);
		int totalPages = (int) Math.ceil((double) totalCar / pageSize);
		model.addAttribute("carlist", car);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("title", "kia");
		model.addAttribute("path", "/product/brand/kia");
		model.addAttribute("content", "kiaFragment");
		model.addAttribute("contentHead", "brandFragmentHead");
		model.addAttribute("pageType", "/product/brand");
		model.addAttribute("brandType", "/product/brand/kia");
		
		return "basic/layout";
	}
	@GetMapping("/brand/samsung")
	public String getBrandSamsungPage(Model model, @RequestParam(defaultValue = "1", name = "page") int page,
			@RequestParam(defaultValue = "8", name = "pageSize") int pageSize) {

		List<Car> car = carService.getCarListByPage(3,page, pageSize);

		int totalCar = carService.getPageCount(3);
		int totalPages = (int) Math.ceil((double) totalCar / pageSize);
		model.addAttribute("carlist", car);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("title", "르노삼성");
		model.addAttribute("path", "/product/brand/samsung");
		model.addAttribute("content", "samsungFragment");
		model.addAttribute("contentHead", "brandFragmentHead");
		model.addAttribute("pageType", "/product/brand");
		model.addAttribute("brandType", "/product/brand/samsung");
		
		return "basic/layout";
	}
	@GetMapping("/brand/chevorlet")
	public String getBrandChevorletPage(Model model, @RequestParam(defaultValue = "1", name = "page") int page,
			@RequestParam(defaultValue = "8", name = "pageSize") int pageSize) {

		List<Car> car = carService.getCarListByPage(4,page, pageSize);

		int totalCar = carService.getPageCount(4);
		int totalPages = (int) Math.ceil((double) totalCar / pageSize);
		model.addAttribute("carlist", car);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
	    model.addAttribute("title", "쉐보레");
	    model.addAttribute("path", "/product/brand/chevorlet");
	    model.addAttribute("content", "chevorletFragment");
	    model.addAttribute("contentHead", "brandFragmentHead");
	    model.addAttribute("pageType", "/product/brand");
	    model.addAttribute("brandType", "/product/brand/chevorlet");

	    return "basic/layout";
	}

	@GetMapping("/brand/ssangyong")
	public String getBrandSsangyongPage(Model model, @RequestParam(defaultValue = "1", name = "page") int page,
			@RequestParam(defaultValue = "8", name = "pageSize") int pageSize) {

		List<Car> car = carService.getCarListByPage(5,page, pageSize);

		int totalCar = carService.getPageCount(5);
		int totalPages = (int) Math.ceil((double) totalCar / pageSize);
		model.addAttribute("carlist", car);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("title", "쌍용");
		model.addAttribute("path", "/product/brand/ssangyong");
		model.addAttribute("content", "ssangyongFragment");
		model.addAttribute("contentHead", "brandFragmentHead");
		model.addAttribute("pageType", "/product/brand");
		model.addAttribute("brandType", "/product/brand/ssangyong");
		
		return "basic/layout";
	}
	@GetMapping("/brand/porshe")
	public String getBrandPorshePage(Model model, @RequestParam(defaultValue = "1", name = "page") int page,
			@RequestParam(defaultValue = "8", name = "pageSize") int pageSize) {

		List<Car> car = carService.getCarListByPage(6,page, pageSize);

		int totalCar = carService.getPageCount(6);
		int totalPages = (int) Math.ceil((double) totalCar / pageSize);
		model.addAttribute("carlist", car);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("title", "포르쉐");
		model.addAttribute("path", "/product/brand/porshe");
		model.addAttribute("content", "porsheFragment");
		model.addAttribute("contentHead", "brandFragmentHead");
		model.addAttribute("pageType", "/product/brand");
		model.addAttribute("brandType", "/product/brand/porshe");
		
		return "basic/layout";
	}
	@GetMapping("/brand/bmw")
	public String getBrandBmwPage(Model model, @RequestParam(defaultValue = "1", name = "page") int page,
			@RequestParam(defaultValue = "8", name = "pageSize") int pageSize) {

		List<Car> car = carService.getCarListByPage(7,page, pageSize);

		int totalCar = carService.getPageCount(7);
		int totalPages = (int) Math.ceil((double) totalCar / pageSize);
		model.addAttribute("carlist", car);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("title", "BMW");
		model.addAttribute("path", "/product/brand/bmw");
		model.addAttribute("content", "bmwFragment");
		model.addAttribute("contentHead", "brandFragmentHead");
		model.addAttribute("pageType", "/product/brand");
		model.addAttribute("brandType", "/product/brand/bmw");
		
		return "basic/layout";
	}
	@GetMapping("/brand/lambo")
	public String getBrandLamboPage(Model model, @RequestParam(defaultValue = "1", name = "page") int page,
			@RequestParam(defaultValue = "8", name = "pageSize") int pageSize) {

		List<Car> car = carService.getCarListByPage(8,page, pageSize);

		int totalCar = carService.getPageCount(8);
		int totalPages = (int) Math.ceil((double) totalCar / pageSize);
		model.addAttribute("carlist", car);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("title", "람보르기니");
		model.addAttribute("path", "/product/brand/lambo");
		model.addAttribute("content", "lamboFragment");
		model.addAttribute("contentHead", "brandFragmentHead");
		model.addAttribute("pageType", "/product/brand");
		model.addAttribute("brandType", "/product/brand/lambo");
		
		return "basic/layout";
	}
	@GetMapping("/smallcar")
	public String getSmallcarPage(Model model, @RequestParam(defaultValue = "1", name = "page") int page,
			@RequestParam(defaultValue = "8", name = "pageSize") int pageSize) {

		List<Car> car = carService.getCarTypeListByPage(1,page, pageSize);

		int totalCar = carService.getTypeCount(1);
		int totalPages = (int) Math.ceil((double) totalCar / pageSize);
		model.addAttribute("carlist", car);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("title", "소형차");
		model.addAttribute("path", "/product/size/smallcar");
		model.addAttribute("content", "smallcarFragment");
		model.addAttribute("contentHead", "sizeFragmentHead");
		model.addAttribute("pageType", "/product/smallcar");
		
		return "basic/layout";
	}
	@GetMapping("/middlecar")
	public String getMiddlecarPage(Model model, @RequestParam(defaultValue = "1", name = "page") int page,
			@RequestParam(defaultValue = "8", name = "pageSize") int pageSize) {

		List<Car> car = carService.getCarTypeListByPage(2,page, pageSize);

		int totalCar = carService.getTypeCount(2);
		int totalPages = (int) Math.ceil((double) totalCar / pageSize);
		model.addAttribute("carlist", car);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("title", "중형차");
		model.addAttribute("path", "/product/size/middlecar");
		model.addAttribute("content", "middlecarFragment");
		model.addAttribute("contentHead", "sizeFragmentHead");
		model.addAttribute("pageType", "/product/middlecar");
		
		return "basic/layout";
	}
	@GetMapping("/bigcar")
	public String getBigcarPage(Model model, @RequestParam(defaultValue = "1", name = "page") int page,
			@RequestParam(defaultValue = "8", name = "pageSize") int pageSize) {

		List<Car> car = carService.getCarTypeListByPage(3,page, pageSize);

		int totalCar = carService.getTypeCount(3);
		int totalPages = (int) Math.ceil((double) totalCar / pageSize);
		model.addAttribute("carlist", car);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("title", "대형차");
		model.addAttribute("path", "/product/size/bigcar");
		model.addAttribute("content", "bigcarFragment");
		model.addAttribute("contentHead", "sizeFragmentHead");
		model.addAttribute("pageType", "/product/bigcar");
		
		return "basic/layout";
	}
	@GetMapping("/sportscar")
	public String getSportscarPage(Model model, @RequestParam(defaultValue = "1", name = "page") int page,
			@RequestParam(defaultValue = "8", name = "pageSize") int pageSize) {

		List<Car> car = carService.getCarTypeListByPage(4,page, pageSize);

		int totalCar = carService.getTypeCount(4);
		int totalPages = (int) Math.ceil((double) totalCar / pageSize);
		model.addAttribute("carlist", car);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("title", "스포츠카");
		model.addAttribute("path", "/product/size/sportscar");
		model.addAttribute("content", "sportscarFragment");
		model.addAttribute("contentHead", "sizeFragmentHead");
		model.addAttribute("pageType", "/product/sportscar");
		
		return "basic/layout";
	}
//	@GetMapping("/buy/{buyId}")
//	public String getBuyPage(@PathVariable("buyId") int buyId, Model model) {
//		
//		Car car = carService.get(buyId);
//		model.addAttribute("carList", car);
//		model.addAttribute("title", "구매페이지");
//		model.addAttribute("path", "/product/sell/userbuy");
//		model.addAttribute("content", "buyFragment");
//		model.addAttribute("contentHead", "buyFragmentHead");
//		
//		return "basic/layout";
//	}
	@GetMapping("/buy/{buyId}")
	public String getBuyPage(@PathVariable("buyId") int buyId, Model model) {
	   
	    int userId = 1;

	    Car car = carService.get(buyId);
	    boolean isLiked = likesService.isLiked(userId, car.getId());

	    model.addAttribute("carList", car);
	    model.addAttribute("isLiked", isLiked);
	    model.addAttribute("title", "구매페이지");
	    model.addAttribute("path", "/product/sell/userbuy");
	    model.addAttribute("content", "buyFragment");
	    model.addAttribute("contentHead", "buyFragmentHead");

	    return "basic/layout";
	}
	@GetMapping("/reservation")
	public String getReservationPage(Model model) {
		
		model.addAttribute("title", "예약페이지");
		model.addAttribute("path", "/product/sell/reservation");
		model.addAttribute("content", "reservationFragment");
		model.addAttribute("contentHead", "reservationFragmentHead");
		
		return "basic/layout";
	}
//	@GetMapping("/like")
//	public String likeCar(@RequestParam Long carId, Model model) {
//	    // 데모용으로 userId를 하드코딩했습니다. 실제 애플리케이션에서는 세션이나 인증 컨텍스트에서 userId를 가져와야 합니다.
//	    Long userId = 1L;
//
//	    boolean isLiked = likesService.isLiked(userId, carId);
//	    likesService.toggleLike(userId, carId);
//
//	    // 차량 목록을 다시 불러옵니다 (실제로는 차량 목록을 가져오는 실제 메서드로 대체해야 합니다).
//	    List<Car> carList = carService.getAllCars();
//	    model.addAttribute("carList", carList);
//	    model.addAttribute("isLiked", isLiked);
//
//	    return "redirect:/product/buy/{carId}";
//	}
	
	
}
