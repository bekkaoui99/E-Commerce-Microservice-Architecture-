package com.hamzabekkaoui.oderservice.repository;

import com.hamzabekkaoui.oderservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address , Long> {
}
