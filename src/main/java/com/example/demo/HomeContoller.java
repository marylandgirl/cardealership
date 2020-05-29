package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeContoller {

    @Autowired
    CarRepository carRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping("/")
    public String index(Model model) {
        Category category = new Category();
        category.setName("SUV");
        categoryRepository.save(category);
        Car car = new Car();
        car.setMake("Honda");
        car.setModel("CRV");
        car.setYear(2021);
        car.setCategory(category);
        carRepository.save(car);

        category = new Category();
        category.setName("Pickup Truck");
        categoryRepository.save(category);

        car = new Car();
        car.setMake("Ford");
        car.setModel("F-150");
        car.setYear(2019);
        car.setCategory(category);
        carRepository.save(car);

        model.addAttribute("cars",carRepository.findAll());
        return "index";
    }
}
