package ru.loylabs.serviceb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.loylabs.serviceb.model.Order;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class JsonUtil {
    private final ObjectMapper objectMapper;

    public String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T deserialize(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> serializeToMap(Order order) {
        return objectMapper.convertValue(order, new TypeReference<>() {
        });
    }

    public Order deserialize(Map<String, Object> orderMap, Class<Order> orderClass) {
        return objectMapper.convertValue(orderMap, orderClass);
    }
}
