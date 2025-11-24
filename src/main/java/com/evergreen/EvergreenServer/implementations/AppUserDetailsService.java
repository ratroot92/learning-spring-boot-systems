package com.evergreen.EvergreenServer.implementations;

import com.evergreen.EvergreenServer.models.AppUser;
import com.evergreen.EvergreenServer.repositories.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class AppUserDetailsService implements UserDetailsService {

    AppUserRepository appUserRepository;

    public AppUserDetailsService(AppUserRepository appUserRepository){
        this.appUserRepository=appUserRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String stringId) throws UsernameNotFoundException {
        Integer userId=Integer.parseInt(stringId);
        if(userId==null){
            throw new UsernameNotFoundException("user not found.");
        }
        AppUser appUser= appUserRepository.findById(userId).orElseThrow(()->new UsernameNotFoundException("user not found."));
        return appUser;
    }


}
