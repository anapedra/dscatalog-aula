package com.anasatanaslopessantantospedra.dscatalog.tests;

import com.anasatanaslopessantantospedra.dscatalog.DTO.ProductDTO;
import com.anasatanaslopessantantospedra.dscatalog.model.Category;
import com.anasatanaslopessantantospedra.dscatalog.model.Product;

import java.time.Instant;

public class Factory {
    public static Product createProduct(){
      Product product=new Product(1L,"phone","Samsung...",10.0,null, Instant.now(),null);
      product.getCategories().add(new Category(4L,"Electronic"));
      return product;
    }
    public static ProductDTO createProductDTO(){
        Product product = createProduct();
        return new ProductDTO(product,product.getCategories());
    }
}
