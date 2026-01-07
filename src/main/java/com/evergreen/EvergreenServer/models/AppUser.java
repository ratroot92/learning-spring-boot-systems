package com.evergreen.EvergreenServer.models;


import com.evergreen.EvergreenServer.security.dtos.ProtectedUserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Table(name = "users")
@Entity(name = "users")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class AppUser  {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name="is_active",nullable = true)
    private Boolean isActive;

    public static  ProtectedUserDto getProtectedUser(AppUser appUser){
        ProtectedUserDto protectedUserDto=new ProtectedUserDto();
        protectedUserDto.setId(appUser.getId());
        protectedUserDto.setEmail(appUser.getEmail());
        protectedUserDto.setUsername(appUser.getUsername());
        protectedUserDto.setPhoneNumber(appUser.getPhoneNumber());
        protectedUserDto.setIsActive(appUser.getIsActive());
        return protectedUserDto;

    }


}
