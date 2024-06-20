package com.E_Commerce_Application.E_Commerce.site.Application.Order.Repository;

import com.E_Commerce_Application.E_Commerce.site.Application.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
