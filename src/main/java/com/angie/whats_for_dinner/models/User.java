package com.angie.whats_for_dinner.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Size(min = 4, message = "Password must be at least 4 characters long")
    private String username;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one letter, one number, and one special character"
    )
    private String password;

    public User() {};

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public @Size(min = 4, message = "Password must be at least 4 characters long") String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 4, message = "Password must be at least 4 characters long") String username) {
        this.username = username;
    }

    public @Size(min = 8, message = "Password must be at least 8 characters long") @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one letter, one number, and one special character"
    ) String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 8, message = "Password must be at least 8 characters long") @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one letter, one number, and one special character"
    ) String password) {
        this.password = password;
    }

}