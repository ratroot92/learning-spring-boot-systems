package com.evergreen.EvergreenServer.security;

import com.evergreen.EvergreenServer.filters.CustomFilter;
import com.evergreen.EvergreenServer.implementations.AppUserDetailsService;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.AuthorizeHttpRequestsDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AppUserDetailsService appUserDetailsService;

    public SecurityConfig(AppUserDetailsService appUserDetailsService){
        this.appUserDetailsService=appUserDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {


         httpSecurity.csrf(Customizer.withDefaults())
                 .csrf((csrf)->csrf.disable())
                 .httpBasic((basic)->basic.disable())
                 .formLogin((formLogin)->formLogin.disable())
                 .anonymous((customizer)->customizer.disable())
                 .authorizeHttpRequests((authorize)->
                                 authorize
                                         .requestMatchers("/api/auth/login","/api/auth/register")
                                         .permitAll()
                                         .requestMatchers("/api/*").authenticated()
                                         .anyRequest().denyAll()


                 );
//                 .addFilterAfter(new CustomFilter(), AuthenticationFilter.class);
         return httpSecurity.build();
    }


    @Bean
   public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        daoAuthenticationProvider.setUserDetailsService(appUserDetailsService);
        return daoAuthenticationProvider;
    }
}
