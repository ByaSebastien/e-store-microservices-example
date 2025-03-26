package be.bstorm.orderservice.services;

import be.bstorm.sharedservice.configs.RabbitMQConfig;
import be.bstorm.sharedservice.models.dtos.OrderEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final RabbitTemplate rabbitTemplate;

    public OrderService(
            @Qualifier("customRabbitTemplate") RabbitTemplate rabbitTemplate
    ) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void createOrder() {
        // Création de l'événement de commande
        OrderEvent orderEvent = new OrderEvent(
                12L,
                45
        );

        // Publication de l'événement vers le service de paiement
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_PAYMENT_EXCHANGE,
                RabbitMQConfig.ORDER_TO_PAYMENT_ROUTING_KEY,
                orderEvent
        );

        System.out.println(("Order created and sent for payment processing: " + orderEvent));
    }
}
