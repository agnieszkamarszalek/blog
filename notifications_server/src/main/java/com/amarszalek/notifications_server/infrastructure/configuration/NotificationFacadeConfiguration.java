package com.amarszalek.notifications_server.infrastructure.configuration;

import com.amarszalek.notifications_server.domain.facades.NotificationFacade;
import com.amarszalek.notifications_server.domain.utils.EmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class NotificationFacadeConfiguration {

    @Bean
    NotificationFacade notificationFacade(EmailService emailService, ExecutorService executorService) {
        return new NotificationFacade(emailService, executorService);
    }

    @Bean
    ExecutorService executorService() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        return Executors.newFixedThreadPool(availableProcessors);
    }

    @Bean
    EmailService emailService() {
        return new EmailService(
                "smtp.gmail.com",
                25,
                "emailAddress@gmail.com",
                "password");
    }
}
