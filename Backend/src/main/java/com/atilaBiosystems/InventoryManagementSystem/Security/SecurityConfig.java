package com.atilaBiosystems.InventoryManagementSystem.Security;

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

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, enabled from users where username=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username, authority from authorities where username=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/raw-materials/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/raw-materials").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/raw-materials/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/raw-materials/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/components").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/components/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/components").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/components/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/components/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/components/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/manufacture").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/manufacture/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/manufacture").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/manufacture/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/manufacture/**").hasRole("ADMIN")
        );
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }
}
