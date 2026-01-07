package com.evergreen.EvergreenServer.implementations;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.evergreen.EvergreenServer.models.AppUser;
import com.evergreen.EvergreenServer.repositories.AppUserRepository;
import com.evergreen.EvergreenServer.security.dtos.UserPrincipal;


@Component
public class AppUserDetailsService implements UserDetailsService {

    AppUserRepository appUserRepository;

    public AppUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String stringId) throws UsernameNotFoundException {
        Integer userId = Integer.parseInt(stringId);
        AppUser appUser = appUserRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User noy found"));
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setUsername(String.valueOf(appUser.getId()));
        userPrincipal.setPassword(appUser.getPassword());
        return userPrincipal;
    }


}
