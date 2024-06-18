package com.E_Commerce_Application.E_Commerce.site.Application.DTO;

import com.E_Commerce_Application.E_Commerce.site.Application.Model.Cart;
import com.E_Commerce_Application.E_Commerce.site.Application.Model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    Cart cart;

    CartItem cartItem;

}
