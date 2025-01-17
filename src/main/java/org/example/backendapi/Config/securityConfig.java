package org.example.backendapi.Config;

import org.example.backendapi.security.JwtRequestFilter;
import org.example.backendapi.security.UserDetailServiceCustom;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class securityConfig {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailServiceCustom userDetailServiceCustom, JwtRequestFilter jwtRequestFilter) throws Exception {
        http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(
                                "/hello",
                                "/login",
                                "/demo/**"
                                ).permitAll()
                                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                                .anyRequest().authenticated()
                )
//                .formLogin(httpSecurityFormLoginConfigurer ->
//                        httpSecurityFormLoginConfigurer
//                                .defaultSuccessUrl("/admin/accounts")
//                                .permitAll()
//                )
                .authenticationProvider(DAOAuthenticationProvider(userDetailServiceCustom))
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider DAOAuthenticationProvider(UserDetailServiceCustom userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
