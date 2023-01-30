package com.fargo.Gwallet.service;

import com.fargo.Gwallet.dto.request.RegistrationRequest;
import com.fargo.Gwallet.model.User;
import jakarta.mail.MessagingException;

public interface UserService {
    String register(RegistrationRequest register) throws MessagingException;
    void enableUser(User user);
}
