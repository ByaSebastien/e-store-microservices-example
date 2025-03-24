package be.bstorm.orderservice.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/{orderId}")
    String getOrder(@PathVariable String orderId) {
        System.out.println("Voici le id : " + orderId);
        return "number 777";
    }
}
