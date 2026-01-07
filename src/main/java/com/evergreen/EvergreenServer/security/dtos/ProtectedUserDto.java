package com.evergreen.EvergreenServer.security.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class ProtectedUserDto {

    private Integer id;
    private String email;
    private String username;
    private String phoneNumber;
    private Boolean isActive;



}
