package rnd.dev.utiliy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import rnd.dev.error.exception.MapperException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MapperUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // object to json
    public static String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new MapperException(e);
        }
    }

    // Json to Object

    public static <T> T deserialize(String jsonString, Class<T> tclass) {
        try {
            return objectMapper.readValue(jsonString.getBytes(StandardCharsets.UTF_8), tclass);
        } catch (IOException e) {
            throw new MapperException(e);
        }

    }
}
