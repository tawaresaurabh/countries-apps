package com.nordea.countries.exceptions;

public class ErrorMessage {
    private String message;
    private MessageType messageType;

    public ErrorMessage(String message) {
        this.message = message;
        this.messageType = MessageType.ERROR;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
