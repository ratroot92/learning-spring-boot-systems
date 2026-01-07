package com.evergreen.EvergreenServer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.evergreen.EvergreenServer.implementations.AppUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AppUserDetailsService appUserDetailsService;

    public SecurityConfig(AppUserDetailsService appUserDetailsService) {
        this.appUserDetailsService = appUserDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.csrf(Customizer.withDefaults()).csrf((csrf) -> csrf.disable()).httpBasic((basic) -> basic.disable())
                .formLogin((formLogin) -> formLogin.disable()).anonymous((customizer) -> customizer.disable())
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/api/auth/login", "/api/auth/register", "/api/jobs/csv/customer")
                        .permitAll().requestMatchers("/api/*").authenticated().anyRequest().denyAll()


                );
        // .addFilterAfter(new CustomFilter(), AuthenticationFilter.class);
        return httpSecurity.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
        return authenticationManager;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(appUserDetailsService);
        return daoAuthenticationProvider;
    }
}
