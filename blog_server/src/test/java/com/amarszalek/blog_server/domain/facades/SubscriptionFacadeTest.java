package com.amarszalek.blog_server.domain.facades;

import com.amarszalek.blog_server.abstractTestClasses.AbstractSubscriptionTest;
import com.amarszalek.blog_server.domain.exceptions.EntityCouldNotBeFoundException;
import com.amarszalek.blog_server.domain.exceptions.EntityNotCreatedException;
import com.amarszalek.blog_server.domain.models.Subscription;
import com.amarszalek.blog_server.domain.repositories.SubscriptionRepository;
import com.amarszalek.blog_server.infrastructure.dtos.SubscriptionDto;
import com.amarszalek.blog_server.infrastructure.dtos.SubscriptionIdDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import javax.persistence.EntityNotFoundException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class SubscriptionFacadeTest extends AbstractSubscriptionTest {
    @MockBean
    SubscriptionRepository subscriptionRepository;
    @Autowired
    SubscriptionFacade subscriptionFacade;
    private Subscription subscription;
    private SubscriptionDto subscriptionDto;

    @Before
    public void setUp() {
        subscriptionDto = super.sampleSubscriptionDto();
        subscription = super.sampleSubscription();
    }

    @Test
    public void shouldReturnExpectedSubscriptionIdDtoWhenSavingSubscription() {
        //given
        subscription.setId(1L);
        SubscriptionIdDto expectedSubscriptionIdDto = new SubscriptionIdDto();
        expectedSubscriptionIdDto.setSubscriptionId(1l);
        when(subscriptionRepository.save(any(Subscription.class)))
                .thenReturn(subscription);
        //when
        SubscriptionIdDto response = subscriptionFacade.saveSubscription(this.subscriptionDto);
        //then
        Assert.assertEquals(expectedSubscriptionIdDto, response);
    }

    @Test(expected = EntityNotCreatedException.class)
    public void shouldThrowEntityNotCreatedExceptionWhenSavingSubscription() {
        //given
        doThrow(new NullPointerException("Entity not created"))
                .when(subscriptionRepository)
                .save(any(Subscription.class));
        //when
        subscriptionFacade.saveSubscription(this.subscriptionDto);
        //then
        //exception specified in test annotation
    }

    @Test(expected = EntityCouldNotBeFoundException.class)
    public void shouldThrowEntityNotCreatedExceptionWhenDeletingSubscription() {
        //given
        doThrow(new EntityNotFoundException())
                .when(subscriptionRepository)
                .deleteById(any(long.class));
        //when
        subscriptionFacade.deleteSubscription(1);
    }
}
