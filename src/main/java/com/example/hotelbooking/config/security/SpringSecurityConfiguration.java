package com.example.hotelbooking.config.security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import java.time.ZonedDateTime;

@Slf4j
@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SpringSecurityConfiguration {

    @Value("${app.info.name}")
    private String globalAppName;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterSecurityChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .exceptionHandling(
                        exceptionHandler ->
                                exceptionHandler.authenticationEntryPoint(authenticationEntryPoint())
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeHttp ->
                                authorizeHttp
                                        .requestMatchers(HttpMethod.POST,
                                                "/api/v1/users").permitAll()
                                        .anyRequest().authenticated()

                )
                .build();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.setHeader("WWW-Authenticate", "Basic realm=\"" + globalAppName + "\"");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");

            String jsonResponse = """
            {
                "message": "Неверные учетные данные пользователя",
                "status": 401,
                "timestamp": "%s"
            }
            """.formatted(ZonedDateTime.now(java.time.ZoneOffset.UTC).toString());

            response.getWriter().write(jsonResponse);
        };
    }

}