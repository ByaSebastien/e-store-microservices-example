package be.bstorm.paymentservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "order-service")
public interface OrderClient {
    @GetMapping("/orders/{orderId}")
    String getOrder(@PathVariable("orderId") String orderId);
}
