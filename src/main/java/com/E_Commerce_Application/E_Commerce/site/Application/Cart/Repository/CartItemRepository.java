package com.E_Commerce_Application.E_Commerce.site.Application.Cart.Repository;

import com.E_Commerce_Application.E_Commerce.site.Application.Model.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);

    List<CartItem> findByCartId(Long Long);

    @Modifying
    @Transactional
    @Query(value = "delete from cart_items where id = :cartItemId", nativeQuery = true)
    void deleteOrderItemById(@Param("cartItemId") Long cartItemId);

}
