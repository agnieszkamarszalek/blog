package com.amarszalek.blog_server.domain.facades;

import com.amarszalek.blog_server.BlogServerApplication;
import com.amarszalek.blog_server.domain.models.Notification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ActiveProfiles(profiles = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BlogServerApplication.class)
@RunWith(SpringRunner.class)
public class NotificationServiceFacadeTest {
    @MockBean
    RestTemplate restTemplate;
    @MockBean
    SubscriptionFacade subscriptionFacade;
    @Autowired
    NotificationServiceFacade notificationServiceFacade;
    private String url = "http://localhost:8090/notifications";

    @Test
    public void shouldVerifyThatMethodSendNotificationListToNotificationServiceWasInvokedWithCorrectParameters()
            throws ExecutionException, InterruptedException {
        //given
        String authorUserName = "any user name";
        List<String> allSubscribed = Arrays.asList("1", "2", "3");
        List<Notification> expectedNotificationList = Arrays.asList(
                new Notification("1",authorUserName + " added new post. Check it out!"),
                new Notification("2",authorUserName + " added new post. Check it out!"),
                new Notification("3",authorUserName + " added new post. Check it out!"));
        when(subscriptionFacade.findAllSubscribed(authorUserName)).thenReturn(allSubscribed);
        //when
        Future future = notificationServiceFacade.sendNotificationListToNotificationService(authorUserName);
        future.get();
        //then
        Mockito.verify(restTemplate).postForEntity(eq(this.url),
                Mockito.eq(expectedNotificationList),
                Mockito.eq(Void.class));
    }
}