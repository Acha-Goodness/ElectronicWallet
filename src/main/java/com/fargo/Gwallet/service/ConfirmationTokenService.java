package com.fargo.Gwallet.service;

import com.fargo.Gwallet.model.ConfirmationToken;
import com.fargo.Gwallet.model.User;

import java.util.UUID;

public interface ConfirmationTokenService {
    String verifyToken(ConfirmationToken confirmToken);
    UUID generateToken();
    void createToken(UUID token, User user);
}
