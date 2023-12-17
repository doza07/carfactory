package com.doza.carfactory.repository;

import com.doza.carfactory.entity.CarBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarBodyRepository extends JpaRepository<CarBody, Integer> {
}
