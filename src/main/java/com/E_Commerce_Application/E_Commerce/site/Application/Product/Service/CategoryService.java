package com.E_Commerce_Application.E_Commerce.site.Application.Product.Service;

import com.E_Commerce_Application.E_Commerce.site.Application.Model.Category;
import com.E_Commerce_Application.E_Commerce.site.Application.Product.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public Category getCategory(Long id){
        Optional<Category> mayBeCategory = categoryRepository.findById(id);
        return mayBeCategory.orElse(null);
    }

    public Optional<Category> getById(Long id){
        return categoryRepository.findById(id);
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
