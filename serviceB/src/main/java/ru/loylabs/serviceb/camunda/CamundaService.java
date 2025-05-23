package ru.loylabs.serviceb.camunda;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.loylabs.serviceb.JsonUtil;
import ru.loylabs.serviceb.model.Order;
import ru.loylabs.serviceb.service.OrderService;

import java.math.BigDecimal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CamundaService {
    private final ZeebeClient zeebeClient;
    private final OrderService orderService;
    private final JsonUtil jsonUtil;

    public void sendOrderToCamunda(Order order) {
        Map<String, Object> orderMap = jsonUtil.serializeToMap(order);
        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("order-process")
                .latestVersion()
                .variables(Map.of("order", orderMap, "total", order.getTotal()))
                .send()
                .join();

    }

    @JobWorker(type = "check-order", fetchVariables = "total")
    public Map<String, Object> check(@Variable("total") BigDecimal total) {
        boolean shouldSave = total != null && total.compareTo(BigDecimal.ZERO) > 0;
        return Map.of("shouldSave", shouldSave);
    }

    @JobWorker(type = "save-order", fetchVariables = {"order", "shouldSave"})
    @Transactional
    public void save(
            @Variable("order") Map<String, Object> orderMap,
            @Variable("shouldSave") Boolean shouldSave) {

        if (Boolean.TRUE.equals(shouldSave)) {
            Order order = jsonUtil.deserialize(orderMap, Order.class);
            orderService.save(order);
        }
    }
}
