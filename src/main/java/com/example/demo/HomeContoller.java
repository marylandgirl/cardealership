package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeContoller {

    private int counter = 0;

    @Autowired
    CarRepository carRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping("/")
    public String index(Model model) {
        counter++;
        if (counter < 2) {
            Category category = new Category();
            category.setName("SUV");
            Set<Car> catsCars = category.getCars();
            Car car = new Car();
            car.setMake("Honda");
            car.setModel("CRV");
            car.setYear(2021);
            car.setCategory(category);
            catsCars.add(car);
            categoryRepository.save(category);
            carRepository.save(car);

            category = new Category();
            category.setName("Pickup Truck");
            catsCars = category.getCars();

            car = new Car();
            car.setMake("Ford");
            car.setModel("F-150");
            car.setYear(2019);
            car.setCategory(category);
            catsCars.add(car);
            categoryRepository.save(category);
            carRepository.save(car);
        }

        model.addAttribute("cars",carRepository.findAll());
        return "index";
    }

    @GetMapping ("/addcategory")
    public String categoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "categoryform";
    }

    @PostMapping("/processcatgory")
    public String processCategory(@Valid @ModelAttribute Category category) {
        System.out.print("Kim the category added is " + category.getName());
        categoryRepository.save(category);
        return "redirect:/listcategories";
    }

    @RequestMapping("/listcategories")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "listcategories";
    }

    @GetMapping("/addcar")
    public String carForm(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("categories",categoryRepository.findAll());
        return "carform";
    }

    @PostMapping("/processcar")
    public String processCar(@Valid @ModelAttribute Car car) {
        carRepository.save(car);
        return "redirect:/";
    }

    @RequestMapping("/updatecar/{id}")
    public String updateCar(@PathVariable("id") long id, Model model ) {
        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("categories",categoryRepository.findAll());
        return "carform";
    }

    @RequestMapping("/updatecategory/{id}")
    public String updateCategory(@PathVariable("id") long id,Model model) {
        model.addAttribute("category",categoryRepository.findById(id).get());
        return "categoryform";
    }

    @RequestMapping("/detail/{id}")
    public String showCar(@PathVariable("id") long id, Model model) {
        model.addAttribute("car", carRepository.findById(id).get());
        return "cardetails";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") long id) {
        carRepository.deleteById(id);
        return "redirect:/";
    }
}
