package com.E_Commerce_Application.E_Commerce.site.Application.Product.Repository;

import com.E_Commerce_Application.E_Commerce.site.Application.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    Optional<Product> findByUserId(Long id);

}
