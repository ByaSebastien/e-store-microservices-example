package be.bstorm.orderservice.controllers;

import be.bstorm.orderservice.services.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    String getOrder(@PathVariable String orderId) {
        System.out.println("Voici le id : " + orderId);
        orderService.createOrder();
        return "number 777";
    }
}
