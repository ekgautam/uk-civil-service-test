package com.ekansh.test.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */
@Slf4j
public class TransformUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Object fromJson(String jsonString, Class<?> valueType) {
        try {
            if (jsonString != null) {
                return objectMapper.readValue(jsonString, valueType);
            }
        } catch (Exception e) {
            log.error("Error in fromJson(), jsonString: " + jsonString + " ; Exception: " + e.getMessage());
        }
        return null;
    }

}
