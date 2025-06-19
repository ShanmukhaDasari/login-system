package com.login.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.login.model.User;
import com.login.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired
    UserService userService;

    // ✅ Signup Endpoint
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        if (userService.isEmailExist(user.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email already exists");
        }

        userService.saveUser(user);
        return ResponseEntity.ok("Signup successful");
    }

    // ✅ Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());

        if (user != null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("role", user.getRole());
            response.put("email", user.getEmail());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    // ✅ Forgot Password Endpoint (simulates sending reset link)
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        boolean status = userService.sendPasswordResetLink(email);

        if (status) {
            return ResponseEntity.ok("Reset link sent to your email.");
        } else {
            return ResponseEntity.status(404).body("Email not found.");
        }
    }

    // ✅ Reset Password Endpoint
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newPassword = request.get("newPassword");

        boolean updated = userService.resetPassword(email, newPassword);
        if (updated) {
            return ResponseEntity.ok("Password has been successfully reset.");
        } else {
            return ResponseEntity.status(404).body("Email not found.");
        }
    }
}
