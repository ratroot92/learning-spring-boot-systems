package com.evergreen.EvergreenServer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.evergreen.EvergreenServer.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
