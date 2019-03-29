package com.amarszalek.notifications_server.domain.utils;

import com.amarszalek.notifications_server.domain.model.Notification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SendNotification implements Runnable {
    private Notification notification;

    @Override
    public void run() {
        new EmailService("smtp.gmail.com", 25, "emailAddress@gmail.com", "password",
                notification.getMessage(), notification.getEmailAddress()
                );
    }
}
