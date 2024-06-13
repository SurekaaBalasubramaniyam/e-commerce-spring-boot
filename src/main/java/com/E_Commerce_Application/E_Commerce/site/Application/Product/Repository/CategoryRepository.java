package com.E_Commerce_Application.E_Commerce.site.Application.Product.Repository;

import com.E_Commerce_Application.E_Commerce.site.Application.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
