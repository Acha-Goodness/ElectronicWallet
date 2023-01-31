package com.fargo.Gwallet.service;

import com.fargo.Gwallet.dto.request.ResendTokenRequest;
import com.fargo.Gwallet.dto.request.VerifyTokenRequest;
import com.fargo.Gwallet.model.ConfirmationToken;
import com.fargo.Gwallet.model.User;
import jakarta.mail.MessagingException;

import java.util.UUID;

public interface ConfirmationTokenService {
    String verifyToken(VerifyTokenRequest verifyTokenRequest);
    String generateToken();
    void createToken(String token, User user);
    String resendToken(ResendTokenRequest resendToken) throws MessagingException;
}
