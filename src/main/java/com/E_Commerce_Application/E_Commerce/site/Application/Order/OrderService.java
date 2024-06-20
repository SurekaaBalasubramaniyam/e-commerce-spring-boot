package com.E_Commerce_Application.E_Commerce.site.Application.Order;

import com.E_Commerce_Application.E_Commerce.site.Application.Model.Cart;
import com.E_Commerce_Application.E_Commerce.site.Application.Model.CartItem;
import com.E_Commerce_Application.E_Commerce.site.Application.Model.Order;
import com.E_Commerce_Application.E_Commerce.site.Application.Model.OrderItem;
import com.E_Commerce_Application.E_Commerce.site.Application.Order.Repository.OrderItemRepository;
import com.E_Commerce_Application.E_Commerce.site.Application.Order.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    public void createOrder(Cart cart) {
        Order order = Order.builder().orderStatus("PENDING").orderTotal(cart.getTotalPrice())
                .createdDate(cart.getCreatedDate()).gstAmount(cart.getGstAmount())
                .totalAmountWithGST(cart.getTotalAmountWithGST()).user(cart.getUser()).build();

        orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem items : cart.getCartItem()) {
            OrderItem orderItem = OrderItem.builder().order(order).itemTotal(items.getItemTotal()).product(items.getProduct())
                    .quantity(items.getQuantity()).gstAmount(items.getGstAmount())
                    .productPrice(items.getProductPrice())
                    .build();

            orderItems.add(orderItem);
        }

        orderItemRepository.saveAll(orderItems);

    }

    public void saveOrderItems(List<OrderItem> orderItems) {
        orderItemRepository.saveAll(orderItems);

    }
}
