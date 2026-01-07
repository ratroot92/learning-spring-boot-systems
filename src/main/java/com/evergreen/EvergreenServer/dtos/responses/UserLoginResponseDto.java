package com.evergreen.EvergreenServer.dtos.responses;

import com.evergreen.EvergreenServer.security.dtos.ProtectedUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponseDto {
    private ProtectedUserDto user;
    private String accessToken;
}
