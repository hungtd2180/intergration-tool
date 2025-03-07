package com.hungtd.utils;


import com.hungtd.entities.Tool;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungtd
 * Date: 06/03/2025
 * Time: 10:27 AM
 * for all issues, contact me: hungtd2180@gmail.com
 */

public class CommonUtil {
    public static String getPropertyName (Class<?> clazz){
        Field[] fields  = clazz.getDeclaredFields();
        List<String> propertyNames = new ArrayList<>();
        for (Field field : fields){
            propertyNames.add(field.getType().getSimpleName());
        }
        return convertListToValue(propertyNames);
    }

    public static String convertListToValue (List<String> list) {
        String value = list.toString().replace('[', '(');
        return value.replace(']', ')');
    }
    public static void main(String[] args) {
//        List<String> test = new ArrayList<>();
//        test.add("name");
//        test.add("description");
//        test.add("url");
//        System.out.println(convertListToValue(test));
        System.out.println(getPropertyName(Tool.class));
    }
}