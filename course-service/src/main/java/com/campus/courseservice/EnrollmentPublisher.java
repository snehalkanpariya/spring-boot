package com.campus.courseservice;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentPublisher {

    private final RabbitTemplate rabbit;

    public EnrollmentPublisher(RabbitTemplate rabbit) { this.rabbit = rabbit; }

    public void publish(EnrollmentEvent event) {
        // fire-and-forget: we do NOT wait for anyone to read it
        rabbit.convertAndSend(RabbitConfig.QUEUE, event);
    }
}