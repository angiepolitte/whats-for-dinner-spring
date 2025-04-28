//package com.angie.whats_for_dinner.configs;
//import org.mindrot.jbcrypt.BCrypt;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class PasswordEncoderConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt());
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
//            }
//        };
//    }
//}