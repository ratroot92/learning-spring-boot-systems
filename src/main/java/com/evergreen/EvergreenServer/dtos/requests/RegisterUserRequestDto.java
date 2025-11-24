package com.evergreen.EvergreenServer.dtos.requests;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequestDto {

    public String email;
    public String password;
    public String confirmPassword;


}
