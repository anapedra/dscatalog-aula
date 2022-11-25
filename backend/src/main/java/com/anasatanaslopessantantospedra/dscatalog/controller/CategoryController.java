package com.anasatanaslopessantantospedra.dscatalog.controller;

import com.anasatanaslopessantantospedra.dscatalog.DTO.CategoryDTO;
import com.anasatanaslopessantantospedra.dscatalog.model.Category;
import com.anasatanaslopessantantospedra.dscatalog.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categoreis")
public class CategoryController {

    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAllCategory(){
       List<CategoryDTO> categories=categoryService.findAllCategory();
       return ResponseEntity.status(HttpStatus.OK).body(categories);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable Long id){
        CategoryDTO categorieDTO=categoryService.findCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categorieDTO);
    }
    @PostMapping
    public ResponseEntity<Object > insertCategory(@RequestBody @Valid CategoryDTO categoryDTO){
        categoryDTO=categoryService.saveCategory(categoryDTO);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(categoryDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(categoryDTO);

    }
}
