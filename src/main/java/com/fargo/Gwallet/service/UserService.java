package com.fargo.Gwallet.service;

import com.fargo.Gwallet.dto.request.response.RegistrationRequest;
import com.fargo.Gwallet.model.User;
import jakarta.mail.MessagingException;

public interface UserService {
    String register(RegistrationRequest register) throws MessagingException;
}
