package com.amarszalek.blog_server.abstractTestClasses;

import com.amarszalek.blog_server.BlogServerApplication;
import com.amarszalek.blog_server.domain.models.Subscription;
import com.amarszalek.blog_server.infrastructure.dtos.SubscriptionDto;
import com.amarszalek.blog_server.infrastructure.dtos.SubscriptionIdDto;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles(profiles = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BlogServerApplication.class)
@RunWith(SpringRunner.class)
public abstract class AbstractSubscriptionTest {

    private String userId = "1";
    private String authorUserName = "aga";

    protected SubscriptionDto sampleSubscriptionDto() {
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        subscriptionDto.setAuthorUserName(authorUserName);
        subscriptionDto.setUserId(userId);
        return subscriptionDto;
    }

    protected Subscription sampleSubscription() {
        Subscription subscription = new Subscription();
        subscription.setAuthorUserName(authorUserName);
        subscription.setUserId(userId);
        return subscription;
    }

    protected SubscriptionIdDto sampleSubscriptionIdDto() {
        return new SubscriptionIdDto();
    }
}
