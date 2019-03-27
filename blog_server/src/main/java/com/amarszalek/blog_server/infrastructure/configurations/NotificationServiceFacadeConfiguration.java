package com.amarszalek.blog_server.infrastructure.configurations;

import com.amarszalek.blog_server.domain.facades.NotificationServiceFacade;
import com.amarszalek.blog_server.domain.facades.SubscriptionFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class NotificationServiceFacadeConfiguration {
    @Bean
    NotificationServiceFacade notificationServiceFacade(
            RestTemplate restTemplate,
            SubscriptionFacade subscriptionFacade){
        return new NotificationServiceFacade(
                restTemplate, "http://localhost:8090/notifications", subscriptionFacade);
    }
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
