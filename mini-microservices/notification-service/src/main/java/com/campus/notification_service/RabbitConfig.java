package com.campus.notification_service;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitConfig {

    @Bean
    public Queue enrollmentQueue() {
        return new Queue("enrollment-events", true);   // SAME name as course-service
    }
@Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter(); // ✅ Recommended for Spring Boot 4.x
    }
}