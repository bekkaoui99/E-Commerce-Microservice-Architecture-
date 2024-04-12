package com.hamzabekkaoui.customerservice.repository;

import com.hamzabekkaoui.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer , Long> {

    boolean existsByUserName(String uerName);
    Optional<Customer> findByUserName(String userName);

    boolean existsByMail(String mail);
    Optional<Customer> findByMail(String mail);
}
