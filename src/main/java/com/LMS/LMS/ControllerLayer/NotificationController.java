package com.LMS.LMS.ControllerLayer;
import com.LMS.LMS.ModelLayer.Notification;
import com.LMS.LMS.ServiceLayer.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{recipientId}")
    public List<Notification> getNotifications(@PathVariable Long recipientId) {
        return notificationService.getNotificationsForRecipient(recipientId);
    }

    @PostMapping("/{id}/markAsRead")
    public void markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
    }
}
