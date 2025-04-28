//package com.angie.whats_for_dinner.configs;
//
//
//import com.angie.whats_for_dinner.services.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.HttpStatusEntryPoint;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private CustomUserDetailsService userDetailsService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private CorsConfigurationSource corsConfigurationSource;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(cors -> cors.configurationSource(corsConfigurationSource))
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.POST, "/register/new-user").permitAll()
//                        .requestMatchers("/api/user/info").permitAll()
//                        .requestMatchers("/api/restaurants/**").permitAll()
//                        .requestMatchers("/api/**").authenticated()
//                        .requestMatchers("/api/favorite-lists/**").authenticated()
//                        .requestMatchers("/register", "/registration", "/api/public/**", "/api/nurseries/**").authenticated()
//                        .anyRequest().permitAll()
//                )
//                .exceptionHandling(e -> e
//                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .successHandler((req, res, auth) -> {
//                            // JSON success
//                            res.setContentType("application/json");
//                            res.getWriter().write("{\"status\":\"ok\"}");
//                            res.setStatus(HttpStatus.OK.value());
//                        })
//                        .failureHandler((req, res, ex) -> {
//                            // JSON failure
//                            res.setContentType("application/json");
//                            res.setStatus(HttpStatus.UNAUTHORIZED.value());
//                            res.getWriter().write("{\"error\":\"Invalid credentials\"}");
//                        })
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                        .permitAll()
//                )
//                .userDetailsService(userDetailsService)
//                .authenticationProvider(authenticationProvider());
//        return http.build();
//    }
//
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        // ... (your authentication provider logic)
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//        return authenticationProvider;
//    }
//}