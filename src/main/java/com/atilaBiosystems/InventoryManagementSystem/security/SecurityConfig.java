package com.atilaBiosystems.InventoryManagementSystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/raw-materials").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/raw-materials/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/raw-materials").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/raw-materials/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/raw-materials/**").hasRole("ADMIN")
        );
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }
}
