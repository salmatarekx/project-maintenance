package com.LMS.LMS.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NotificationDTO {

    @NotNull(message = "Recipient ID is required")
    public Long recipientId;

    @NotNull(message = "Sender ID is required")
    public Long senderId;

    @NotBlank(message = "Message cannot be blank")
    public String message;

    @NotBlank(message = "Type is required")
    public String type;

    private boolean isRead;

    public Long getRecipientId() {
        return recipientId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public boolean getIsRead() {
        return isRead();
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
