package com.fargo.Gwallet.service;

import com.fargo.Gwallet.Repository.ConfirmationTokenRepository;
import com.fargo.Gwallet.dto.request.VerifyTokenRequest;
import com.fargo.Gwallet.model.ConfirmationToken;
import com.fargo.Gwallet.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    UserService userService;

    @Override
    public String verifyToken(VerifyTokenRequest verifyToken) {
        ConfirmationToken savedToken = confirmationTokenRepository.findByTokenNumberIgnoreCase(verifyToken.getTokenNumber())
                .orElseThrow(() -> new IllegalArgumentException("TOKEN DOES NOT EXIST"));

        if(savedToken.getExpiredAt().isBefore(LocalDateTime.now())) return "TOKEN HAS EXPIRED";
        savedToken.setConfirmedAt(LocalDateTime.now());
        userService.enableUser(savedToken.getUser());
        confirmationTokenRepository.save(savedToken);

        return "VERIFIED";
    }

    @Override
    public String generateToken() {

        return UUID.randomUUID().toString().substring(0, 5);
    }

    @Override
    public void createToken(String token, User user) {
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setTokenNumber(token);
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiredAt(LocalDateTime.now().plusMinutes(10));
        confirmationToken.setUser(user);;

        confirmationTokenRepository.save(confirmationToken);
    }
}
