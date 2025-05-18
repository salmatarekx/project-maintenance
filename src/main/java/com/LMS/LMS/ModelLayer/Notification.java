package com.LMS.LMS.ModelLayer;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Recipient ID cannot be null")
    private Long recipientId;

    @NotNull(message = "Sender ID cannot be null")
    private Long senderId;

    @NotBlank(message = "Message cannot be blank")
    @Size(max = 500, message = "Message cannot exceed 500 characters")
    private String message;

    @NotBlank(message = "Type cannot be blank")
    @Pattern(regexp = "COURSE_UPDATED|LESSON_ADDED|OTHER_TYPE", message = "Invalid notification type")
    private String type;

    @Column(name = "created_at")
    @NotNull(message = "Created at timestamp cannot be null")
    private LocalDateTime createdAt = LocalDateTime.now();

    private boolean isRead = false;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getRecipientId() { return recipientId; }
    public void setRecipientId(Long recipientId) { this.recipientId = recipientId; }

    public Long getSenderId() { return senderId; }
    public void setSenderId(Long senderId) { this.senderId = senderId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { isRead = read; }
}
