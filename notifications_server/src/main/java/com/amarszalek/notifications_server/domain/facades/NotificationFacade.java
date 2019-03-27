package com.amarszalek.notifications_server.domain.facades;

import com.amarszalek.notifications_server.domain.model.Notification;
import java.util.List;

public class NotificationFacade {

    public void sendNotifications(List<Notification> notifications) {
        for (Notification notification: notifications) {
            System.out.println(notification.getMessage());
        }
    }
}
