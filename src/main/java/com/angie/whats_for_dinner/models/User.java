package com.angie.whats_for_dinner.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(name= "username")
    @Size(min = 4, message = "Username must be at least 4 characters long")
    private String username;

    @Column(name= "password")
    @Size(min = 8, message = "Password must be at least 8 characters long")

    private String password;

    public User() {}

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
