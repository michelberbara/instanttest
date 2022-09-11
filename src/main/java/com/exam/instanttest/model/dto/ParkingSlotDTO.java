package com.exam.instanttest.model.dto;

import lombok.Data;

@Data
public class ParkingSlotDTO {
    private String recordId;
    private String name;
    private Double capacity;
    private Double remainingSlots;
    private Object geometry;
    private Double distance;
}
