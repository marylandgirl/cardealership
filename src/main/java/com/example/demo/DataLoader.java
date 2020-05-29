package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    CarRepository carRepository;

    @Autowired
    CategoryRepository categoryRepository;
    

    @Override
    public void run(String... args) throws Exception {
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
}
