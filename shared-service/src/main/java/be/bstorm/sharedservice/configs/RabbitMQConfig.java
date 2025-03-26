package be.bstorm.sharedservice.configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String ORDER_PAYMENT_EXCHANGE = "order-payment-exchange";
    public static final String ORDER_TO_PAYMENT_ROUTING_KEY = "order.payment.route";
    public static final String PAYMENT_TO_ORDER_ROUTING_KEY = "payment.order.route";
    public static final String ORDER_QUEUE = "order-queue";
    public static final String PAYMENT_QUEUE = "payment-queue";

    @Bean
    public Queue orderQueue() {
        return QueueBuilder.durable(ORDER_QUEUE).build();
//        return new Queue(ORDER_QUEUE,false);
    }

    @Bean
    public Queue paymentQueue() {
        return QueueBuilder.durable(PAYMENT_QUEUE).build();
//        return new Queue(PAYMENT_QUEUE,false);
    }

    @Bean
    public DirectExchange orderPaymentExchange() {
        return new DirectExchange(ORDER_PAYMENT_EXCHANGE);
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, DirectExchange orderPaymentExchange) {
        return BindingBuilder
                .bind(orderQueue)
                .to(orderPaymentExchange)
                .with(ORDER_TO_PAYMENT_ROUTING_KEY);
    }

    @Bean
    public Binding paymentBinding(Queue paymentQueue, DirectExchange orderPaymentExchange) {
        return BindingBuilder
                .bind(paymentQueue)
                .to(orderPaymentExchange)
                .with(PAYMENT_TO_ORDER_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @Qualifier("customRabbitTemplate")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
