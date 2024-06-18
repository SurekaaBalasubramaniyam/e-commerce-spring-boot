package com.E_Commerce_Application.E_Commerce.site.Application.Product.Service;

import com.E_Commerce_Application.E_Commerce.site.Application.DTO.ProductDTO;
import com.E_Commerce_Application.E_Commerce.site.Application.Model.Category;
import com.E_Commerce_Application.E_Commerce.site.Application.Model.Product;
import com.E_Commerce_Application.E_Commerce.site.Application.Model.User;
import com.E_Commerce_Application.E_Commerce.site.Application.Product.Repository.CategoryRepository;
import com.E_Commerce_Application.E_Commerce.site.Application.Product.Repository.ProductRepository;
import com.E_Commerce_Application.E_Commerce.site.Application.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Product addProduct(ProductDTO productDTO) throws Exception  {
        Product product = productDTO.getProduct();
        Category category = categoryRepository.findById(productDTO.getTempCategoryId()).orElse(new Category());
        product.setCategory(category);

        User user = userRepository.findById(productDTO.getTemporaryUserId()).orElse(new User());
        product.setUser(user);
        return productRepository.save(product);

    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(Long id, Product updatedProduct) throws Exception  {

        Optional<Product> maybeProduct = productRepository.findById(id);

        if(maybeProduct.isPresent()) {

            Product oldProduct = maybeProduct.get();
            oldProduct.setProduct_name(updatedProduct.getProduct_name());
            oldProduct.setDescription(updatedProduct.getDescription());
            oldProduct.setPrice(updatedProduct.getPrice());
            oldProduct.setGstPercentage(updatedProduct.getGstPercentage());

            Category category = categoryRepository.findById(updatedProduct.getTempCategoryId()).orElseThrow(() -> new Exception("Category not found"));
            oldProduct.setCategory(category);

            productRepository.save(oldProduct);
        }else{
            throw new Exception("Product not Found");
        }

    }
}
