package com.example.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/").permitAll()
                        .pathMatchers("/actuator/**").hasRole("ADMIN")
                        .anyExchange().authenticated())
                .httpBasic(withDefaults())
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(ServerHttpSecurity.CorsSpec::disable)
                //.cors(withDefaults())
                //.cors(corsSpec -> corsSpec.configurationSource(urlBasedCorsConfigurationSource()))
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        return new MapReactiveUserDetailsService(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    private UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.applyPermitDefaultValues();
//        // corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setAllowedHeaders(List.of("*"));
//        corsConfiguration.setAllowedMethods(List.of("*"));
//        corsConfiguration.setAllowedOrigins(List.of("*"));
//        UrlBasedCorsConfigurationSource ccs = new UrlBasedCorsConfigurationSource();
//        ccs.registerCorsConfiguration("/**", corsConfiguration);
//        return ccs;
//    }

}