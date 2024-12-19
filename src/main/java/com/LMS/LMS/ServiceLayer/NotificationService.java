package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.DTO.NotificationDTO;
import com.LMS.LMS.ModelLayer.Notification;
import com.LMS.LMS.RepositoryLayer.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    public Notification createNotification(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setRecipientId(notificationDTO.getRecipientId());
        notification.setSenderId(notificationDTO.GetSenderId());
        notification.setMessage(notificationDTO.getMessage());
        notification.setType(notificationDTO.getType());
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsForRecipient(Long recipientId) {
        return notificationRepository.findByRecipientId(recipientId);
    }

    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        notificationRepository.save(notification);
    }
}
