package com.doza.carfactory.controller;


import com.doza.carfactory.dto.CarCreationRequest;
import com.doza.carfactory.entity.Car;
import com.doza.carfactory.entity.CarBody;
import com.doza.carfactory.entity.CarWheels;
import com.doza.carfactory.repository.CarBodyRepository;
import com.doza.carfactory.repository.CarWheelRepository;
import com.doza.carfactory.service.CarService;
import com.doza.carfactory.util.CustomErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;
    private final CarBodyRepository carBodyRepository;
    private final CarWheelRepository carWheelRepository;

    @Autowired
    public CarController(CarService carService, CarBodyRepository carBodyRepository, CarWheelRepository carWheelRepository) {
        this.carService = carService;
        this.carBodyRepository = carBodyRepository;
        this.carWheelRepository = carWheelRepository;
    }

    @GetMapping("/getAllCarBody")
    public ResponseEntity<List<CarBody>> getAllCarBody() {
        List<CarBody> carBodies = carBodyRepository.findAll();
        return new ResponseEntity<>(carBodies, HttpStatus.OK);
    }

    @GetMapping("/getAllCarWheels")
    public ResponseEntity<List<CarWheels>> getAllCarWheels() {
        List<CarWheels> carWheels = carWheelRepository.findAll();
        return new ResponseEntity<>(carWheels, HttpStatus.OK);
    }

    @PostMapping("/createCar")
    public ResponseEntity<?> createCar(@RequestBody CarCreationRequest request) {
        try {
            Car createdCar = carService.createCar(request);
            return new ResponseEntity<>(createdCar, HttpStatus.CREATED);
        } catch (IllegalArgumentException | NoSuchElementException ex) {
            String errorMessage = ex.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    @GetMapping("/getCar/{carId}")
    public ResponseEntity<?> getCarById(@PathVariable int carId) {

        Car car = carService.getCarById(carId);
        if (car == null) {
            String errorMessage = "Auto with ID " + carId + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomErrorResponse(errorMessage));
        } else
            return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @GetMapping("/getCars")
    public ResponseEntity<Page<Car>> getCarsWithPagination(Pageable pageable) {
        Page<Car> cars = carService.getCarsWithPagination(pageable);
        return ResponseEntity.ok(cars);
    }
}



