package com.E_Commerce_Application.E_Commerce.site.Application.Product.Controller;

import com.E_Commerce_Application.E_Commerce.site.Application.Model.Category;
import com.E_Commerce_Application.E_Commerce.site.Application.Product.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> GetAllCategory(){
        List<Category> CategoryList =  categoryService.getAllCategory();
        return ResponseEntity.ok(CategoryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> GetById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> UpdateCategory(@PathVariable Long id, @RequestBody Category Category){
        Optional<Category> category = categoryService.getById(id);
        if(category.isPresent()){
            Category updatedCategory = category.get();
            updatedCategory.setName(Category.getName());
            updatedCategory.setDescription(Category.getDescription());
            return ResponseEntity.ok(categoryService.saveCategory(updatedCategory));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
