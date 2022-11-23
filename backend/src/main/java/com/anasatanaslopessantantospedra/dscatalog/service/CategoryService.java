package com.anasatanaslopessantantospedra.dscatalog.service;

import com.anasatanaslopessantantospedra.dscatalog.model.Category;
import com.anasatanaslopessantantospedra.dscatalog.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public Page<Category> findAllCategory(Pageable pageable){
        Page<Category> categories=categoryRepository.findAll(pageable);
        return categories;
    }
}
