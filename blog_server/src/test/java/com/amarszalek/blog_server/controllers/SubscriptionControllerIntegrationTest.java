package com.amarszalek.blog_server.controllers;

import com.amarszalek.blog_server.abstractTestClasses.AbstractSubscriptionTest;
import com.amarszalek.blog_server.domain.models.Subscription;
import com.amarszalek.blog_server.domain.repositories.SubscriptionRepository;
import com.amarszalek.blog_server.infrastructure.dtos.SubscriptionDto;
import com.amarszalek.blog_server.infrastructure.dtos.SubscriptionIdDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class SubscriptionControllerIntegrationTest extends AbstractSubscriptionTest {
    @Autowired
    TestRestTemplate testRestTemplate;
    private SubscriptionDto subscriptionDto;
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @LocalServerPort
    int serverPort;
    private String url;

    @Before
    public void setUp() {
        url = "http://localhost:" + serverPort + "/subscriptions";
        subscriptionDto = super.sampleSubscriptionDto();
    }

    @Test
    public void shouldSaveSubscription() {
        //when
        ResponseEntity<SubscriptionIdDto> response = testRestTemplate.postForEntity(url, subscriptionDto, SubscriptionIdDto.class);
        //then
        Optional<Subscription> subscriptionOptional = subscriptionRepository.findById(response.getBody().getSubscriptionId());
        Assert.assertTrue(subscriptionOptional.isPresent());
    }

    @Test
    public void shouldDeleteSubscription() {
        //given
        ResponseEntity<SubscriptionIdDto> response = testRestTemplate.postForEntity(url, subscriptionDto, SubscriptionIdDto.class);
        long subscriptionId = response.getBody().getSubscriptionId();
        //when
        testRestTemplate.delete(url + "/" + subscriptionId);
        //then
        Optional<Subscription> deletedSubscription = subscriptionRepository.findById(subscriptionId);
        Assert.assertFalse(deletedSubscription.isPresent());
    }
}
