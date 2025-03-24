package be.bstorm.paymentservice.controllers;

import be.bstorm.paymentservice.clients.OrderClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final OrderClient orderClient;

    public PaymentController(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    @PostMapping("/{orderId}")
    public String processPayment(@PathVariable String orderId) {
        System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
        String order = orderClient.getOrder(orderId);
        return "Paiement validé pour la commande : " + order;
    }
}
