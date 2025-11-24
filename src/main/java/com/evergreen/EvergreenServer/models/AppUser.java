package com.evergreen.EvergreenServer.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "users")
@Data
public class AppUser implements UserDetails {

    @Id()
    @Column(name="id")
    private int id;

    @Column(name = "name",nullable = true)
    private String name;

    @Column(name="email",nullable = true,unique = true)
    private String email;

    @Column(name="phone",nullable = true,unique = true)
    private String phoneNumber;

    @Column(name="username",nullable = true,unique = true)
    private String username;

    @Column(name="password",nullable = true)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
