package be.bstorm.orderservice.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final RabbitTemplate rabbitTemplate;

    public OrderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public String createOrder(@RequestBody Map<String, String> order) {
        rabbitTemplate.convertAndSend("order.exchange", "order.routingKey", order);
        return "Commande créée !";
    }

    @GetMapping("/orders/{orderId}")
    String getOrder(@PathVariable String orderId) {
        System.out.println("Voici le id : " + orderId);
        return "number 777";
    }
}
