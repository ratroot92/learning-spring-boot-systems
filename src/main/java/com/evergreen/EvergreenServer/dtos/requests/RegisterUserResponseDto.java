package com.evergreen.EvergreenServer.dtos.requests;


import com.evergreen.EvergreenServer.security.dtos.ProtectedUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class RegisterUserResponseDto {
    public ProtectedUserDto user;
    public String accessToken;
}
