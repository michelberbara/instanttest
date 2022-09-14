package com.exam.instanttest.service;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exam.instanttest.model.dto.ParkingSlotDTO;
import com.exam.instanttest.variables.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ParkingServiceTest {
    @InjectMocks
    private HttpService httpService = new HttpService();

    ParkingService parkingService;

    @BeforeEach
    void init() {
        this.parkingService = new ParkingService(httpService);
    }

    @Test
    void testGetParkings() throws UnirestException {
        HttpResponse<String> parkings = httpService.getRequest(Constants.PARKINGS_URL.getUrl(), null);
        assertEquals(200, parkings.getCode());
        assertNotNull(parkings.getBody());
    }

    @Test
    void testGetSlots() throws UnirestException {
        HttpResponse<String> slots = httpService.getRequest(Constants.PARKING_SLOTS_URL.getUrl(), null);
        assertEquals(200, slots.getCode());
        assertNotNull(slots.getBody());
    }

    @Test
    void testGetParkingSlots() throws JsonMappingException, JsonProcessingException, UnirestException {
        List<ParkingSlotDTO> parkingSlotDTOs = parkingService.getParkingSlots(1.0, 1.0);
        assertNotNull(parkingSlotDTOs);
        assert(parkingSlotDTOs.size() > 0);
    }
}
