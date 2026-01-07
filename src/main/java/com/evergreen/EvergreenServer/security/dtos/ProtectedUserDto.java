package com.evergreen.EvergreenServer.security.dtos;

import com.evergreen.EvergreenServer.models.AppUser;
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
    private String phone;
    private Boolean isActive;



}
