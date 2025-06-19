package com.login.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data                   // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor      // Generates no-arg constructor
@AllArgsConstructor     // Generates constructor with all fields
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String role = "USER"; // default role
}
