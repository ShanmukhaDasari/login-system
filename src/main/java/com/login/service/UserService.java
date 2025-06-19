package com.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.login.model.User;
import com.login.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    EmailService emailService;

    public boolean isEmailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    // ✅ Forgot Password Token Generator (simulated)
    public boolean sendPasswordResetLink(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            String token = UUID.randomUUID().toString(); // generate random token
            String resetLink = "http://localhost:8008/reset-password.html?token=" + token;

//            // ✅ Send actual email using EmailService
//            String subject = "Reset Your Password";
//            String body = "Click the link below to reset your password:\n\n" + resetLink;
//
//            emailService.sendEmail(email, subject, body); // sending email
//            return true;
            
            emailService.sendEmail(email, "Reset Password", "Click this link: " + resetLink);
            return true;
        }
        return false;
    }

    // ✅ Actual password reset logic
    public boolean resetPassword(String email, String newPassword) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }

	
}
