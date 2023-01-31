package com.fargo.Gwallet.service;

import com.fargo.Gwallet.Repository.UserRepository;
import com.fargo.Gwallet.dto.request.Login;
import com.fargo.Gwallet.dto.request.RegistrationRequest;
import com.fargo.Gwallet.model.Role;
import com.fargo.Gwallet.model.User;
import com.fargo.Gwallet.utils.EmailSender;
import com.fargo.Gwallet.utils.Validator;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailSender emailSender;
    @Autowired
    ConfirmationTokenService confirmationTokenService;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public String register(RegistrationRequest register) throws MessagingException {
        if(!Validator.isValidEmailAddress(register.getEmail()))
            throw new IllegalArgumentException("EMAIL ADDRESS IS NOT IN THE RIGHT FORMAT");

        if(!Validator.isValidPassword(register.getPassword()))
            throw new IllegalArgumentException("PASSWORD IS NOT IN THE RIGHT FORMAT");

        if(userRepository.findUserByEmailAddressIgnoreCase(register.getEmail()).isPresent())
            throw new IllegalArgumentException("USER ALREADY EXIST");

        User user = new User();
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setEmailAddress(register.getEmail());
        user.setPassword(register.getPassword());
        user.setUserRole(Role.USER);

        userRepository.save(user);

        String token = confirmationTokenService.generateToken();

        confirmationTokenService.createToken(token, user);

        emailSender.send(user.getEmailAddress(), emailSender.buildEmail(user.getFirstName(), token));

        return "REGISTRATION SUCCESSFUL";
    }

    @Override
    public void enableUser(User user) {
        user.setEnable(true);
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
         return userRepository.findUserByEmailAddressIgnoreCase(email)
                    .orElseThrow(() -> new IllegalArgumentException("EMAIL ADDRESS DOES NOT EXIST"));

    }

    @Override
    public String logInUser(Login login) {
        User user = userRepository.findUserByEmailAddressIgnoreCase(login.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("INCORRECT EMAIL ADDRESS"));

        if(!login.getPassword().equals(user.getPassword())) throw new IllegalArgumentException("INCORRECT PASSWORD");
        if(!user.isEnable()) throw new IllegalArgumentException("ACCOUNT NOT ENABLE, VERIFY YOUR TOKEN");
        return "LOGIN IS SUCCESSFUL";
    }
}
