package com.E_Commerce_Application.E_Commerce.site.Application.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="orderItem")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    @Transient
    private Long tempOrderId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @Transient
    private Long tempsProductId;

    private Long quantity;
    private double gstAmount;
    private double productPrice;
    private double itemTotal;

}
