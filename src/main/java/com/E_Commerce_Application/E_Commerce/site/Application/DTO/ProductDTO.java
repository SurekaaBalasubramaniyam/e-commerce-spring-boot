package com.E_Commerce_Application.E_Commerce.site.Application.DTO;

import com.E_Commerce_Application.E_Commerce.site.Application.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    Product product;

    private Long temporaryUserId;
    private Long tempCategoryId;

}
