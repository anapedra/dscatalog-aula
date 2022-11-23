package com.anasatanaslopessantantospedra.dscatalog.controller;

import com.anasatanaslopessantantospedra.dscatalog.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categoreis")
public class CategoryController {

    @GetMapping
    public ResponseEntity<List<Category>> findAllCategory(){
        List<Category> categories=new ArrayList<>();
        categories.add(new Category(1L,"books"));
        categories.add(new Category(2L,"eletrodomestico"));
        categories.add(new Category(3L,"eletronico"));
        categories.add(new Category(4L,"alimentos"));

        return ResponseEntity.ok().body(categories);
    }
}
