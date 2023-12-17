package com.doza.carfactory.repository;

import com.doza.carfactory.entity.CarWheels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface CarWheelRepository extends JpaRepository<CarWheels, Integer> {
}
