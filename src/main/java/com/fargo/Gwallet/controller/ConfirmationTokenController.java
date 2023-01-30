package com.fargo.Gwallet.controller;


import com.fargo.Gwallet.model.ConfirmationToken;
import com.fargo.Gwallet.service.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/token")
public class ConfirmationTokenController {

    @Autowired
    ConfirmationTokenService confirmationTokenService;

    @PostMapping("/confirmToken")
    public ResponseEntity<?> verifyToken(@RequestBody ConfirmationToken confirmToken){
//        return new ResponseEntity<>(confirmationTokenService.verifyToken())
        return null;
    }
}