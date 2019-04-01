package com.amarszalek.notifications_server.domain.utils;

import com.amarszalek.notifications_server.domain.model.Notification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SendNotification implements Runnable {
    private Notification notification;
    private EmailService emailService;

    @Override
    public void run() {
        this.emailService.sendMail(notification.getMessage(), notification.getEmailAddress());
    }
}
