package com.E_Commerce_Application.E_Commerce.site.Application.Cart.Repository;

import com.E_Commerce_Application.E_Commerce.site.Application.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long id);
}
