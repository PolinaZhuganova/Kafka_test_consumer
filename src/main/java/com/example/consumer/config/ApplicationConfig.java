package com.example.consumer.config;

import org.springframework.context.annotation.*;

/**
 * Класс ApplicationConfig
 */
@Configuration
@Import({ConsumerConfig.class, WebConfig.class})
@PropertySource({
	"classpath:kafka.properties"
})
@ComponentScan({
	"com.example.consumer.config",
	"com.example.consumer.controller",
	"com.example.consumer.service",
})
public class ApplicationConfig {

}