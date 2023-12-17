package com.doza.carfactory.service;

import com.doza.carfactory.dto.CarCreationRequest;
import com.doza.carfactory.entity.Car;
import com.doza.carfactory.entity.CarBody;
import com.doza.carfactory.entity.CarWheels;
import com.doza.carfactory.repository.CarBodyRepository;
import com.doza.carfactory.repository.CarRepository;
import com.doza.carfactory.repository.CarWheelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.*;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarBodyRepository carBodyRepository;
    private final CarWheelRepository carWheelRepository;


    @Autowired
    public CarService(CarRepository carRepository, CarBodyRepository carBodyRepository, CarWheelRepository carWheelRepository) {
        this.carRepository = carRepository;
        this.carBodyRepository = carBodyRepository;
        this.carWheelRepository = carWheelRepository;
    }

    public Car getCarById(int id) {
        Optional<Car> car = carRepository.findById(id);
        return car.orElse(null);
    }

    public List<CarBody> getAllCarBody() {
        return carBodyRepository.findAll();
    }

    public List<CarWheels> getAllCarWheels() {
        return carWheelRepository.findAll();
    }


    @Transactional
    public Car createCar(CarCreationRequest request) {

        int carBodyId = request.getCarBodyId();
        List<Integer> carWheelIds = request.getCarWheelIds();
        String coolName = request.getCoolName();

        CarBody carBody = carBodyRepository.findById(carBodyId)
                .orElseThrow(() -> new NoSuchElementException("Корпус с ID " + carBodyId + " не найден."));

        if (carBody.getQuantity() < 1) {
            throw new IllegalArgumentException("Недостаточно корпусов с ID " + carBody.getId());
        }

        carBody.setQuantity(carBody.getQuantity() - 1);


        List<CarWheels> selectedWheels = new ArrayList<>();
        for (Integer wheelId : carWheelIds) {
            CarWheels carWheels = carWheelRepository.findById(wheelId)
                    .orElseThrow(() -> new NoSuchElementException("Колесо с ID " + wheelId + " не найдено."));
            selectedWheels.add(carWheels);
        }

        if (selectedWheels.size() < 2) {
            throw new IllegalArgumentException("Необходимо выбрать как минимум 2 колеса.");
        }

        for (CarWheels selectedWheel : selectedWheels) {
            if (selectedWheel.getQuantity() <= 0) {
                throw new IllegalArgumentException("Недостаточно колес с ID " + selectedWheel.getId());
            }
        }

        for (CarWheels selectedWheel : selectedWheels) {
            selectedWheel.setQuantity(selectedWheel.getQuantity() - 1);
        }

        Car car = new Car(request.getCoolName(), carBody, selectedWheels);
        car = carRepository.save(car);

        return car;
    }

    @Transactional(readOnly = true)
    public Page<Car> getCarsWithPagination(Pageable pageable) {
        return carRepository.findAll(pageable);
    }
}