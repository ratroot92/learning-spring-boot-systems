package com.evergreen.EvergreenServer.services;


import com.evergreen.EvergreenServer.dtos.requests.RegisterUserRequestDto;
import com.evergreen.EvergreenServer.models.AppUser;
import com.evergreen.EvergreenServer.repositories.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository){
        this.appUserRepository=appUserRepository;
    }


    public AppUser registerUser(RegisterUserRequestDto registerUserRequestDto) throws Exception {
        AppUser newUser=new AppUser();
        newUser.setEmail(registerUserRequestDto.getEmail());
        newUser.setPassword(registerUserRequestDto.getPassword());
        try {
            return this.appUserRepository.save(newUser);
        }
        catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
