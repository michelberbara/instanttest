package com.exam.instanttest.model.domain;

import lombok.Data;

@Data
public class Geometry {
    private double longitude;
    private double latitude;

    public Geometry(){

    }
    public Geometry(double longitude, double latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
