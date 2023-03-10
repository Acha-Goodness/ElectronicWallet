package com.fargo.Gwallet.utils;

import jakarta.mail.MessagingException;

import java.util.UUID;

public interface EmailSender {
    void send(String to, String email)  throws MessagingException;

    String buildEmail(String name, String token);


}
