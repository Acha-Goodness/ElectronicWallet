package com.fargo.Gwallet.service;

import com.fargo.Gwallet.Repository.ConfirmationTokenRepository;
import com.fargo.Gwallet.model.ConfirmationToken;
import com.fargo.Gwallet.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public String verifyToken(ConfirmationToken confirmToken) {
        return null;
    }

    @Override
    public UUID generateToken() {
        return UUID.randomUUID();
    }

    @Override
    public void createToken(UUID token, User user) {
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setTokenNumber(token);
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiredAt(LocalDateTime.now().plusMinutes(10));
        confirmationToken.setUser(user);;

        confirmationTokenRepository.save(confirmationToken);
    }
}
