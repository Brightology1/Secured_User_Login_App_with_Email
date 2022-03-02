package com.brightology.securedlogin.email;

public interface EmailSender {
    void send(String to, String email);
}
