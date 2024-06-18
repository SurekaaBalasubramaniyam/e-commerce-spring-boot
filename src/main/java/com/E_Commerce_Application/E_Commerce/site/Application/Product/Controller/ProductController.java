package com.E_Commerce_Application.E_Commerce.site.Application.Product.Controller;

import com.E_Commerce_Application.E_Commerce.site.Application.DTO.ProductDTO;
import com.E_Commerce_Application.E_Commerce.site.Application.Model.Product;
import com.E_Commerce_Application.E_Commerce.site.Application.Product.Repository.CategoryRepository;
import com.E_Commerce_Application.E_Commerce.site.Application.Product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> product = productService.getAllProduct();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) throws Exception {
        try{
            productService.addProduct(productDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "User does not Exist", e);
        }
        return ResponseEntity.ok(productService.addProduct(productDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) throws Exception {
        productService.updateProduct(id,productDetails);
        return  ResponseEntity.ok("Product Updated Successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("delete success");
    }

}
