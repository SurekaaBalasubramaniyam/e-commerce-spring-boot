package com.E_Commerce_Application.E_Commerce.site.Application.Cart;

import com.E_Commerce_Application.E_Commerce.site.Application.Cart.Repository.CartItemRepository;
import com.E_Commerce_Application.E_Commerce.site.Application.Cart.Repository.CartRepository;
import com.E_Commerce_Application.E_Commerce.site.Application.DTO.CartDTO;
import com.E_Commerce_Application.E_Commerce.site.Application.Model.Cart;
import com.E_Commerce_Application.E_Commerce.site.Application.Model.CartItem;
import com.E_Commerce_Application.E_Commerce.site.Application.Model.Product;
import com.E_Commerce_Application.E_Commerce.site.Application.Model.User;
import com.E_Commerce_Application.E_Commerce.site.Application.Order.OrderService;
import com.E_Commerce_Application.E_Commerce.site.Application.Order.Repository.OrderItemRepository;
import com.E_Commerce_Application.E_Commerce.site.Application.Product.Repository.ProductRepository;
import com.E_Commerce_Application.E_Commerce.site.Application.User.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    private OrderService orderService;

    @Transactional
    public void addToCart(CartDTO cartDTO) throws Exception {

        Long userId = cartDTO.getCart().getTempUserId();

        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User does not exists"));

        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart());

        cart.setUser(user);

        cart = cartRepository.save(cart);

        Product product = productRepository.findById(cartDTO.getCartItem().getTempProductId()).orElseThrow(() -> new Exception("Product does not exists"));

        Long quantity = cartDTO.getCartItem().getQuantity();
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setProductPrice(product.getPrice());
        calculateTotal(cartItem, cart, product);

        cartRepository.save(cart);

    }

    private void calculateTotal(CartItem cartItem, Cart cart, Product product) {

        double itemTotal = cartItem.getQuantity() * product.getPrice();
        double gstAmount = itemTotal * (product.getGstPercentage() / 100 ) ;
        cartItem.setItemTotal(itemTotal);
        cartItem.setGstAmount(gstAmount);

        cartItemRepository.save(cartItem);

        List<CartItem> cartItemList = cartItemRepository.findByCartId(cart.getId());

        double finalTotal = cartItemList.stream().mapToDouble(item -> item.getItemTotal()).sum();
        double gstTotal = cartItemList.stream().mapToDouble(item -> item.getGstAmount()).sum();
        double finalTotalWithGst = finalTotal + gstTotal;

        cart.setTotalPrice(finalTotal);
        cart.setGstAmount(gstTotal);
        cart.setTotalAmountWithGST(finalTotalWithGst);

    }

    public Cart getCartById(Long id) throws Exception {
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart updateCart(Long cartId, CartItem cartItem) throws Exception {
        Cart cart = getCartById(cartId);

        CartItem existingItem = cartItemRepository.findByCartIdAndProductId(cart.getId(),
                cartItem.getTempProductId()).orElseThrow(() -> new Exception("cart item not found"));

        Product product = productRepository.findById(cartItem.getTempProductId())
                .orElseThrow(() -> new Exception("Product not found"));

        existingItem.setQuantity(cartItem.getQuantity());
        existingItem.setProductPrice(product.getPrice());

        calculateTotal(existingItem, cart, product);

        return cartRepository.save(cart);
    }

    @Transactional
    public void deleteCartItem(Long cartIItemId) throws Exception {
        CartItem cartItem = cartItemRepository.findById(cartIItemId).orElseThrow( () -> new Exception("Cart Item Not Found"));

        Cart cart = cartRepository.findById(cartItem.getCart().getId()).orElseThrow( () -> new Exception("Cart Not Found"));

        cartItemRepository.deleteOrderItemById(cartIItemId);

        System.out.println("Cart item deleted successfully");

        List<CartItem> existingItems = cartItemRepository.findByCartId(cart.getId());

        double finalTotal = existingItems.stream().mapToDouble(item -> item.getItemTotal()).sum();
        double gstTotal = existingItems.stream().mapToDouble(item -> item.getGstAmount()).sum();
        double finalTotalWithGst = finalTotal + gstTotal;

        cart.setTotalPrice(finalTotal);
        cart.setGstAmount(gstTotal);
        cart.setTotalAmountWithGST(finalTotalWithGst);
        cartRepository.save(cart);

    }

    public void createOrder(Long userId) throws Exception {

        Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> new Exception("User does not exists"));

        orderService.createOrder(cart);

    }

}
