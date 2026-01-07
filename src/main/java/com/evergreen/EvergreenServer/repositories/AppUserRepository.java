package com.evergreen.EvergreenServer.repositories;

import com.evergreen.EvergreenServer.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Integer> {

    AppUser findByEmail(String email);
}
