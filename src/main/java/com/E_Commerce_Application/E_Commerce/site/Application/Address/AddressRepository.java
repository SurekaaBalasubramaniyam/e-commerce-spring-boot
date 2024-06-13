package com.E_Commerce_Application.E_Commerce.site.Application.Address;

import com.E_Commerce_Application.E_Commerce.site.Application.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
