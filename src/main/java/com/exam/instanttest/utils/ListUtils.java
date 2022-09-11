package com.exam.instanttest.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ListUtils {

    public static List<Object> convertObjectToList(Object obj) {
        List<Object> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[]) obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>) obj);
        }
        return list;
    }

}
