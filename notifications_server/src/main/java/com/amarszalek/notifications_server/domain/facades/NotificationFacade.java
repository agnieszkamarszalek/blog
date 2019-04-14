package com.amarszalek.notifications_server.domain.facades;

import com.amarszalek.notifications_server.domain.model.Notification;
import com.amarszalek.notifications_server.domain.utils.EmailService;
import com.amarszalek.notifications_server.domain.utils.SendNotification;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@AllArgsConstructor
public class NotificationFacade {

    private EmailService emailService;
    private ExecutorService executorService;

    public void sendNotifications(List<Notification> notifications) {
        for (Notification notification: notifications) {
            executorService.execute(new SendNotification(notification, this.emailService));
        }
    }
}
