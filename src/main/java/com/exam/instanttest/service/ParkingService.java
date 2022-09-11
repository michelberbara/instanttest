package com.exam.instanttest.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.instanttest.model.BaseResponse;
import com.exam.instanttest.model.domain.Parking;
import com.exam.instanttest.model.domain.Slot;
import com.exam.instanttest.model.dto.ParkingSlotDTO;
import com.exam.instanttest.variables.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

import math.geom2d.Point2D;

@Service
public class ParkingService {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private HttpService httpService;

    public List<Parking> getParkings() throws UnirestException, JsonMappingException, JsonProcessingException {
        HttpResponse<String> response = httpService.getRequest(Constants.PARKINGS_URL.getUrl(), null);
        BaseResponse baseResponse = objectMapper.readValue(response.getBody().toString(), BaseResponse.class);

        List<Parking> parkings = Parking.mapToListOfParkings(baseResponse.getRecords());

        return parkings;
    }

    public List<Slot> getSlots() throws UnirestException, JsonMappingException, JsonProcessingException {
        HttpResponse<String> response = httpService.getRequest(Constants.PARKING_SLOTS_URL.getUrl(), null);
        BaseResponse baseResponse = objectMapper.readValue(response.getBody().toString(), BaseResponse.class);

        List<Slot> slots = Slot.mapToListOfSlots(baseResponse.getRecords());
        return slots;
    }

    public List<ParkingSlotDTO> getParkingSlots(Double longitude, Double latitude)
            throws JsonMappingException, JsonProcessingException, UnirestException {

        List<Parking> parkings = getParkings();
        List<Slot> slots = getSlots();
        List<ParkingSlotDTO> parkingSlotDTOs = new ArrayList();

        parkings.forEach(parking -> {
            Slot slot = slots.stream().filter(x -> parking.getName().equals(x.getName()))
                    .findFirst().orElse(null);
            if (slot != null) {
                ParkingSlotDTO parkingSlotDTO = new ParkingSlotDTO();
                parkingSlotDTO.setRecordId(parking.getRecordid());
                parkingSlotDTO.setName(parking.getName());
                parkingSlotDTO.setGeometry(parking.getGeometry());
                parkingSlotDTO.setCapacity(slot.getCapacity());
                parkingSlotDTO.setRemainingSlots(slot.getRemainingSlots());
                parkingSlotDTOs.add(parkingSlotDTO);
                parkingSlotDTO.setDistance(Point2D.distance(latitude, longitude, parking.getGeometry().getLatitude(),
                        parking.getGeometry().getLongitude()));
            }
        });

        return parkingSlotDTOs.stream().sorted(Comparator.comparing(ParkingSlotDTO::getDistance)).toList();
    }

}
