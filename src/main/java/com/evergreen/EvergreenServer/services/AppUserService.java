package com.evergreen.EvergreenServer.services;


import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.evergreen.EvergreenServer.advices.ApiException;
import com.evergreen.EvergreenServer.dtos.requests.RegisterUserRequestDto;
import com.evergreen.EvergreenServer.dtos.requests.RegisterUserResponseDto;
import com.evergreen.EvergreenServer.dtos.requests.UserLoginRequestDto;
import com.evergreen.EvergreenServer.dtos.responses.UserLoginResponseDto;
import com.evergreen.EvergreenServer.models.AppUser;
import com.evergreen.EvergreenServer.repositories.AppUserRepository;
import com.evergreen.EvergreenServer.security.JwtService;
import com.evergreen.EvergreenServer.security.dtos.ProtectedUserDto;
import com.evergreen.EvergreenServer.utils.AppLogger;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    @Autowired
    private AppLogger appLogger;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public UserLoginResponseDto loginUser(UserLoginRequestDto userLoginDto) {
        String password = userLoginDto.getPassword();
        String email = userLoginDto.getEmail();

        AppUser user = appUserRepository.findByEmail(email);
        if (user == null) {
            throw ApiException.unAuthenticated("User not found.");
        }

        String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        if (hashedPassword != password) {
            throw ApiException.unAuthenticated("Incorrect password.");

        }
        ProtectedUserDto protectedUserDto = AppUser.getProtectedUser(user);
        String accessToken = jwtService.generateJwtToken(user);
        UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto();
        userLoginResponseDto.setUser(protectedUserDto);
        userLoginResponseDto.setAccessToken(accessToken);
        return userLoginResponseDto;



    }


    public RegisterUserResponseDto registerUser(RegisterUserRequestDto registerUserRequestDto) {
        String password = registerUserRequestDto.getPassword();
        String confirmPassword = registerUserRequestDto.getConfirmPassword();
        String email = registerUserRequestDto.getEmail();
        appLogger.log(password);
        appLogger.log(bCryptPasswordEncoder.encode(password));
        appLogger.log(email);
        if (!Objects.equals(password, confirmPassword)) {
            throw ApiException.badRequest("Password and Confirm Password do not match.");
        }
        AppUser alreadyExistsByEmail = appUserRepository.findByEmail(email);
        if (alreadyExistsByEmail != null) {
            throw ApiException.badRequest("User already exists with  email '" + email + "' .");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        AppUser newUser = new AppUser();
        newUser.setEmail(email);
        newUser.setPassword(encodedPassword);
        newUser = this.appUserRepository.save(newUser);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(newUser.getId(), password));
        if (!authentication.isAuthenticated()) {
            appLogger.log(" => " + authentication.isAuthenticated());
            throw ApiException.unAuthenticated("Not authenticated");
        }
        String accessToken = jwtService.generateJwtToken(newUser);
        return RegisterUserResponseDto.build(AppUser.getProtectedUser(newUser), accessToken);

    }

}
