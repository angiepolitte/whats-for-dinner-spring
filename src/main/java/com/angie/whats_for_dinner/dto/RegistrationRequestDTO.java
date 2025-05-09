package com.angie.whats_for_dinner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationRequestDTO {
    private Long id;
    private String username;
    private String password;
}