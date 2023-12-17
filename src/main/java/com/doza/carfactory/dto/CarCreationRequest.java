package com.doza.carfactory.dto;

import java.util.List;

public class CarCreationRequest {

    private Integer carBodyId;
    private List<Integer> carWheelIds;
    private String coolName;

    public CarCreationRequest() {
    }

    public CarCreationRequest(Integer carBodyId, List<Integer> carWheelIds, String coolName) {
        this.carBodyId = carBodyId;
        this.carWheelIds = carWheelIds;
        this.coolName = coolName;
    }

    public Integer getCarBodyId() {
        return carBodyId;
    }

    public void setCarBodyId(Integer carBodyId) {
        this.carBodyId = carBodyId;
    }

    public List<Integer> getCarWheelIds() {
        return carWheelIds;
    }

    public void setCarWheelIds(List<Integer> carWheelIds) {
        this.carWheelIds = carWheelIds;
    }

    public String getCoolName() {
        return coolName;
    }

    public void setCoolName(String coolName) {
        this.coolName = coolName;
    }
}

