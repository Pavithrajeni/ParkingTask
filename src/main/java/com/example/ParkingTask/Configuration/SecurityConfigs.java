package com.example.ParkingTask.Configuration;

import com.example.ParkingTask.Security.JwtFilter;
import com.example.ParkingTask.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


@Configuration
public class SecurityConfigs {
    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }


        @Bean
        public DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();

            provider.setUserDetailsService(customUserDetailsService);

            provider.setPasswordEncoder(passwordEncoder());

            return provider;
        }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .authenticationProvider(authenticationProvider())
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(
                                            "/User/Register",
                                            "/User/login",
                                            "/v3/api-docs/**",
                                            "/swagger-ui/**",
                                            "/swagger-ui.html"
                                    ).permitAll()
                            .requestMatchers("/User/admin/**").hasRole("ADMIN")
                            .requestMatchers("/Tariff/**").hasRole("ADMIN")
                            .requestMatchers("/parking/**").hasAnyRole("USER","ADMIN")
                            .anyRequest().authenticated()
                    );

            return http.build();
        }

}
