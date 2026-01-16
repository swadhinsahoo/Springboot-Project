package net.javaguides.sms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Enables asynchronous execution across the application.
 */
@Configuration
@EnableAsync
public class AsyncConfig {
}
