package com.fargo.Gwallet.controller;

import com.fargo.Gwallet.dto.request.ForgotPasswordRequest;
import com.fargo.Gwallet.dto.request.LoginRuquest;
import com.fargo.Gwallet.dto.request.RegistrationRequest;
import com.fargo.Gwallet.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest) throws MessagingException {
        return new ResponseEntity<>(userService.register(registrationRequest),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRuquest login){
        return new ResponseEntity<>(userService.logInUser(login), HttpStatus.OK);
    }

    @PostMapping("/forgotPassword/{userId}")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest passwordRequest, @PathVariable Long userId){
        return new ResponseEntity<>(userService.forgotPassword(passwordRequest, userId), HttpStatus.OK);
    }
}
