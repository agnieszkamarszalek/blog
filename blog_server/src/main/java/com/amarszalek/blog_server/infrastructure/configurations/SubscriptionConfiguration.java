package com.amarszalek.blog_server.infrastructure.configurations;

import com.amarszalek.blog_server.domain.facades.SubscriptionFacade;
import com.amarszalek.blog_server.domain.repositories.SubscriptionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
public class SubscriptionConfiguration {
    @Bean
    SubscriptionFacade subscriptionFacade(SubscriptionRepository subscriptionRepository, ModelMapper modelMapper){
        return new SubscriptionFacade(subscriptionRepository, modelMapper);
    }
}
