package com.amarszalek.notifications_server.domain.facades;

import com.amarszalek.notifications_server.domain.model.Notification;
import com.amarszalek.notifications_server.domain.utils.SendNotification;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotificationFacade {

    public void sendNotifications(List<Notification> notifications) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (Notification notification: notifications) {
            executorService.execute(new SendNotification(notification));
        }
    }
}
