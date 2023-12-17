package com.doza.carfactory.entity;

import jakarta.persistence.*;


import java.util.List;


@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "cool_name")
    private String coolName;

    @ManyToOne
    @JoinColumn(name = "car_body_id")
    private CarBody carBody;

    @ManyToMany
    @JoinTable(
            name = "car_wheels",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "car_wheel_id"))
    private List<CarWheels> carWheels;



    public Car() {
    }

    public Car(String coolName, CarBody carBody, List<CarWheels> carWheels) {
        this.coolName = coolName;
        this.carBody = carBody;
        this.carWheels = carWheels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoolName() {
        return coolName;
    }

    public void setCoolName(String coolName) {
        this.coolName = coolName;
    }

    public CarBody getCarBody() {
        return carBody;
    }

    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    public List<CarWheels> getCarWheels() {
        return carWheels;
    }

    public void setCarWheels(List<CarWheels> carWheels) {
        this.carWheels = carWheels;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", coolName='" + coolName + '\'' +
                ", carBody=" + carBody +
                ", carWheels=" + carWheels +
                '}';
    }
}
