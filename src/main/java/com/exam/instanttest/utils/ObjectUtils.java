package com.exam.instanttest.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectUtils {
    public static Map<String, Object> convertToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                map.put(field.getName(), field.get(obj));
            } catch (Exception e) {
            }
        }
        return map;
    }
}
