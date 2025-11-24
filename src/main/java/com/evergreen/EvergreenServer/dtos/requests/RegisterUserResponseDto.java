package com.evergreen.EvergreenServer.dtos.requests;


import com.evergreen.EvergreenServer.models.AppUser;
import lombok.Data;

@Data
public class RegisterUserResponseDto {
    public AppUser user;
    public String accessToken;
}
