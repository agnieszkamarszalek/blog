package com.amarszalek.notifications_server.api.controller;

import com.amarszalek.notifications_server.domain.facades.NotificationFacade;
import com.amarszalek.notifications_server.domain.model.Notification;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {
    private NotificationFacade notificationFacade;

    @PostMapping(consumes="application/json")
    public void sendNotifications(@RequestBody List<Notification> notifications) {
        notificationFacade.sendNotifications(notifications);
    }
}
