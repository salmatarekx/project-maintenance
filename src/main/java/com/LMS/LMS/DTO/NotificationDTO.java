package com.LMS.LMS.DTO;

public class NotificationDTO {
    public Long recipientId;
    public Long senderId;
    public String message;
    public String type;

    public Long getRecipientId(){
        return recipientId ;
    }
    public Long GetSenderId(){
        return senderId;
    }
    public String getMessage(){
        return message ;
    }
    public String getType(){
        return type ;
    }
}
