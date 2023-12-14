package com.kyungil.webcar.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kyungil.webcar.product.domain.Car;
import com.kyungil.webcar.product.service.CarService;

@Controller
public class MainController {
    @Autowired
    private CarService carService;

    @GetMapping("/")
    public String main(Model model) {
        List<Car> hot = carService.getHotList();
        List<Car> bestDefault = carService.getBestList(1);
        List<Car> bestSmall = bestDefault;
        List<Car> bestMiddle = carService.getBestList(2);
        List<Car> bestLarge = carService.getBestList(3);
        List<Car> bestSports = carService.getBestList(4);

        model.addAttribute("bestSmall", bestSmall);
        model.addAttribute("bestMiddle", bestMiddle);
        model.addAttribute("bestLarge", bestLarge);
        model.addAttribute("bestSports", bestSports);
        model.addAttribute("hot", hot);
        model.addAttribute("title", "메인페이지");
        model.addAttribute("imgPath", "/basic/img");
        model.addAttribute("imgContent", "imgFragment");
        model.addAttribute("imgContentHead", "imgFragmentHead");
        model.addAttribute("path", "/main/main");
        model.addAttribute("content", "mainFragment");
        model.addAttribute("contentHead", "mainFragmentHead");

        return "basic/layout";
    }

    @GetMapping("/bestSmall")
    public String getBestSmall(Model model) {
        List<Car> bestSmall = carService.getBestList(1);
        model.addAttribute("bestSmall", bestSmall);
        return "fragments/carListFragment :: carList";
    }

    @GetMapping("/bestMiddle")
    public String getBestMiddle(Model model) {
        List<Car> bestMiddle = carService.getBestList(2);
        model.addAttribute("bestMiddle", bestMiddle);
        return "fragments/carListFragment :: carList";
    }

    @GetMapping("/bestLarge")
    public String getBestLarge(Model model) {
        List<Car> bestLarge = carService.getBestList(3);
        model.addAttribute("bestLarge", bestLarge);
        return "fragments/carListFragment :: carList";
    }

    @GetMapping("/bestSports")
    public String getBestSports(Model model) {
        List<Car> bestSports = carService.getBestList(4);
        model.addAttribute("bestSports", bestSports);
        return "fragments/carListFragment :: carList";
    }
}
