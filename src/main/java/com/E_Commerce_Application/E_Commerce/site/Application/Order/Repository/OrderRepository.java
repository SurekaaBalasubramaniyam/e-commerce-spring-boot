package com.E_Commerce_Application.E_Commerce.site.Application.Order.Repository;

import com.E_Commerce_Application.E_Commerce.site.Application.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
