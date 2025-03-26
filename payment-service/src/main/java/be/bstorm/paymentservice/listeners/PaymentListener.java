package be.bstorm.paymentservice.listeners;

import be.bstorm.sharedservice.configs.RabbitMQConfig;
import be.bstorm.sharedservice.models.dtos.OrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentListener {

//    private final RabbitTemplate rabbitTemplate;

//    public PaymentListener(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }

    @RabbitListener(queues = {RabbitMQConfig.ORDER_QUEUE})
    public void processPayment(OrderEvent orderEvent) {

        // Renvoyer le résultat au service de commande
//            rabbitTemplate.convertAndSend(
//                    RabbitMQConfig.ORDER_PAYMENT_EXCHANGE,
//                    RabbitMQConfig.PAYMENT_TO_ORDER_ROUTING_KEY,
//                    orderEvent
//            );
        System.out.println("Payment processed for order: " + orderEvent);
    }
}
