package com.amarszalek.notifications_server.infrastructure.configuration;

import com.amarszalek.notifications_server.domain.facades.NotificationFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationFacadeConfiguration {

    @Bean
    NotificationFacade notificationFacade() {
        return new NotificationFacade();
    }
}
