package com.exam.instanttest.model.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.exam.instanttest.utils.ListUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class Parking {
    private String datasetid;
    private String recordid;
    private String name;
    private String address;
    private Geometry geometry;

    public static List<Parking> mapToListOfParkings(Object rawParking) {
        List<Parking> parkings = new ArrayList<>();
        List<Object> rawParkings = ListUtils.convertObjectToList(rawParking);

        rawParkings.forEach(parking -> {
            parkings.add(mapObjectToParking(parking));
        });

        return parkings;
    }

    public static Parking mapObjectToParking(Object rawParking) {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> mappedParking = objectMapper.convertValue(rawParking, HashMap.class);
        HashMap<String, Object> fields = objectMapper.convertValue(mappedParking.get("fields"), HashMap.class);

        Parking parking = new Parking();
        parking.setDatasetid(String.valueOf(mappedParking.get("datasetid")));
        parking.setRecordid(String.valueOf(mappedParking.get("recordid")));
        parking.setName(String.valueOf(fields.get("nom")));
        parking.setAddress(String.valueOf(fields.get("adresse")));

        HashMap<String, Object> geoMap = objectMapper.convertValue(mappedParking.get("geometry"), HashMap.class);

        List<Object> geoLocation = ListUtils.convertObjectToList(geoMap.get("coordinates"));

        parking.setGeometry(
                new Geometry(Double.valueOf(String.valueOf(geoLocation.get(0))),
                        Double.valueOf(String.valueOf(geoLocation.get(1)))));

        return parking;
    }
}
