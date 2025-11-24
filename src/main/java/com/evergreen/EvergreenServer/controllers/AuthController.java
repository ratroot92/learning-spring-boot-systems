package com.evergreen.EvergreenServer.controllers;
import com.evergreen.EvergreenServer.dtos.requests.RegisterUserRequestDto;
import com.evergreen.EvergreenServer.dtos.requests.RegisterUserResponseDto;
import com.evergreen.EvergreenServer.models.AppUser;
import com.evergreen.EvergreenServer.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AppUserService appUserService;

    public AuthController(AppUserService appUserService){
        this.appUserService=appUserService;
    }

    @PostMapping("login")
    public String login(){
        return "Hy";
    }

    @PostMapping("register")
    public ResponseEntity<RegisterUserResponseDto> register(@RequestBody RegisterUserRequestDto registerUserRequestDto) throws Exception {
        AppUser appUser= this.appUserService.registerUser(registerUserRequestDto);
        RegisterUserResponseDto registerUserResponseDto=new RegisterUserResponseDto();
        registerUserResponseDto.setUser(appUser);
       return new ResponseEntity<RegisterUserResponseDto>(registerUserResponseDto, HttpStatus.OK);

    }

    @GetMapping("/me")
    public String me(){
        return "Me";
    }
}
