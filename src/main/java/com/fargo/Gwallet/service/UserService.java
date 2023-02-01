package com.fargo.Gwallet.service;

import com.fargo.Gwallet.dto.request.ForgotPasswordRequest;
import com.fargo.Gwallet.dto.request.LoginRuquest;
import com.fargo.Gwallet.dto.request.RegistrationRequest;
import com.fargo.Gwallet.model.User;
import jakarta.mail.MessagingException;

public interface UserService {
    void save(User user);
    String register(RegistrationRequest register) throws MessagingException;
    void enableUser(User user);
    User findUserByEmail(String email);
    String logInUser(LoginRuquest login);
    String forgotPassword(ForgotPasswordRequest passwordRequest, Long id);
}
