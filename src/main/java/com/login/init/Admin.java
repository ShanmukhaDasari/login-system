package com.login.init;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.login.model.User;
import com.login.repository.UserRepository;

@Component
public class Admin{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostConstruct
    public void initAdminUser() {
        if (userRepository.findByEmail("siva@gmail.com").isEmpty()) {
            User admin = new User();
            admin.setEmail("siva@gmail.com");
            admin.setPassword(encoder.encode("siva123")); // hashed password
            admin.setRole("ADMIN");
            userRepository.save(admin);
            System.out.println("✅ Admin user created!");
        }
    }
}
