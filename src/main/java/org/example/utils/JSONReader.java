package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class JSONReader {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> List<T> readJson(String filePath, Class<T[]> clazz) {
        try {
            return List.of(objectMapper.readValue(new File(filePath), clazz));
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}

