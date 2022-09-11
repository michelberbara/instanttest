package com.exam.instanttest.model.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.exam.instanttest.utils.ListUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class Slot {
    private String datasetid;
    private String recordid;
    private String name;
    private double capacity;
    private double remainingSlots;

    public static List<Slot> mapToListOfSlots(Object rawSlot) {
        List<Slot> slots = new ArrayList<>();
        List<Object> rawSlots = ListUtils.convertObjectToList(rawSlot);

        rawSlots.forEach(slot -> {
            slots.add(mapObjectToSlot(slot));
        });

        return slots;
    }

    public static Slot mapObjectToSlot(Object rawSlot) {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> mappedSlot = objectMapper.convertValue(rawSlot, HashMap.class);
        HashMap<String, Object> fields = objectMapper.convertValue(mappedSlot.get("fields"), HashMap.class);

        Slot slot = new Slot();
        slot.setDatasetid(String.valueOf(mappedSlot.get("datasetid")));
        slot.setRecordid(String.valueOf(mappedSlot.get("recordid")));
        slot.setName(String.valueOf(fields.get("nom")));
        slot.setCapacity(Double.valueOf(String.valueOf(fields.get("capacite"))));
        slot.setRemainingSlots(Double.valueOf(String.valueOf(fields.get("places_restantes"))));

        return slot;
    }

}
