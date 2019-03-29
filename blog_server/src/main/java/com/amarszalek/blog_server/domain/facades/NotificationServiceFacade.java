package com.amarszalek.blog_server.domain.facades;

import com.amarszalek.blog_server.domain.models.Notification;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@AllArgsConstructor
public class NotificationServiceFacade {
    private RestTemplate restTemplate;
    private String url;
    private SubscriptionFacade subscriptionFacade;

    @Async
    public Future sendNotificationListToNotificationService(String authorUserName) {
        List<String> allSubscribed = subscriptionFacade.findAllSubscribed(authorUserName);
        List<Notification> notificationList =
                allSubscribed.stream()
                        .map(subscribedEmailAddress ->
                                new Notification(authorUserName + " added new post. Check it out!", subscribedEmailAddress)
                        )
                        .collect(Collectors.toList());
        if (!notificationList.isEmpty()) {
            restTemplate.postForEntity(url, notificationList, Void.class);
        }
        return new AsyncResult("");
    }
}
