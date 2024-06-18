package com.E_Commerce_Application.E_Commerce.site.Application.Cart;

import com.E_Commerce_Application.E_Commerce.site.Application.DTO.CartDTO;
import com.E_Commerce_Application.E_Commerce.site.Application.Model.Cart;
import com.E_Commerce_Application.E_Commerce.site.Application.Model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public String addToCart(@RequestBody CartDTO cartDTO) throws Exception {
        try {
            cartService.addToCart(cartDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "User does not exist", e);
        }
        return "success";
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Long id) throws Exception {
        return cartService.getCartById(id);
    }

    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable Long id, @RequestBody CartItem cartItem) throws Exception {
        return cartService.updateCart(id, cartItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemFromCart(@PathVariable Long id) throws Exception {
        cartService.removeItemFromCart(id);
        return ResponseEntity.ok("delete success");
    }

}
