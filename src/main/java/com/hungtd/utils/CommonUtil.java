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
        List<String> propertyNames = new ArrayList<String>();
        for (Field field : fields){
            propertyNames.add(field.getName());
        }
        String propertyName = propertyNames.toString().replace('[', '(');
        return propertyName.replace(']', ')');

    }

    public static void main(String[] args) {
        System.out.println(getPropertyName(Tool.class));
    }
}