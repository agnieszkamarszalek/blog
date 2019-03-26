package com.amarszalek.blog_server.controllers;

import com.amarszalek.blog_server.abstractTestClasses.AbstractSubscriptionTest;
import com.amarszalek.blog_server.domain.exceptions.EntityCouldNotBeFoundException;
import com.amarszalek.blog_server.domain.exceptions.EntityNotCreatedException;
import com.amarszalek.blog_server.domain.facades.SubscriptionFacade;
import com.amarszalek.blog_server.infrastructure.dtos.SubscriptionDto;
import com.amarszalek.blog_server.infrastructure.dtos.SubscriptionIdDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class SubscriptionControllerTest extends AbstractSubscriptionTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    SubscriptionFacade subscriptionFacade;
    @Autowired
    TestRestTemplate testRestTemplate;
    private SubscriptionDto subscriptionDto;
    private SubscriptionIdDto subscriptionIdDto;
    @LocalServerPort
    int serverPort;
    private String url;

    @Before
    public void setUp() {
        url = "http://localhost:" + serverPort + "/subscriptions";
        subscriptionDto = super.sampleSubscriptionDto();
        subscriptionIdDto = super.sampleSubscriptionIdDto();
    }

    @Test
    public void shouldReturnStatusCreatedWhenSavingSubscription() {
        //given
        when(subscriptionFacade.saveSubscription(any(SubscriptionDto.class)))
                .thenReturn(subscriptionIdDto);
        //when
        ResponseEntity<SubscriptionIdDto> response = testRestTemplate.postForEntity(url, subscriptionDto, SubscriptionIdDto.class);
        //then
        HttpStatus expectedStatusCode = response.getStatusCode();
        Assert.assertEquals(HttpStatus.CREATED, expectedStatusCode);
    }

    @Test
    public void shouldReturnStatusBadRequestWhenSavingSubscription() {
        //given
        doThrow(new EntityNotCreatedException("Entity not created"))
                .when(subscriptionFacade)
                .saveSubscription(any(SubscriptionDto.class));
        //when
        ResponseEntity<SubscriptionIdDto> response = testRestTemplate.postForEntity(url, subscriptionDto, SubscriptionIdDto.class);
        //then
        HttpStatus expectedStatusCode = response.getStatusCode();
        Assert.assertEquals(HttpStatus.BAD_REQUEST, expectedStatusCode);
    }

    @Test
    public void shouldReturnExpectedSubscriptionIdDtoWhenSavingSubscription() {
        //given
        when(subscriptionFacade.saveSubscription(any(SubscriptionDto.class)))
                .thenReturn(subscriptionIdDto);
        //when
        ResponseEntity<SubscriptionIdDto> response = testRestTemplate.postForEntity(url, subscriptionDto, SubscriptionIdDto.class);
        //then
        SubscriptionIdDto subscriptionIdDtoSaved = response.getBody();
        Assert.assertEquals(subscriptionIdDto, subscriptionIdDtoSaved);
    }

    @Test
    public void shouldReturnStatusOkWhenDeletingSubscription() throws Exception {
        //given
        doNothing().when(subscriptionFacade).deleteSubscription(any(long.class));
        //when
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(url + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusNotFoundWhenDeletingSubscription() throws Exception {
        //given
        doThrow(new EntityCouldNotBeFoundException("Entity couldn't be deleted"))
                .when(subscriptionFacade)
                .deleteSubscription(any(long.class));
        //when
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(url + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isNotFound());
    }

}
