package com.anasatanaslopessantantospedra.dscatalog.service;

import com.anasatanaslopessantantospedra.dscatalog.DTO.CategoryDTO;
import com.anasatanaslopessantantospedra.dscatalog.model.Category;
import com.anasatanaslopessantantospedra.dscatalog.repository.CategoryRepository;
import com.anasatanaslopessantantospedra.dscatalog.service.exceptions.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAllCategory(){
        List<Category> list=categoryRepository.findAll();
        return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());

    }
    @Transactional(readOnly = true)
    public CategoryDTO findCategoryById(Long id){
     Optional<Category> obj=categoryRepository.findById(id);
     Category entity=obj.orElseThrow(
             ()-> new EntityNotFoundException("Id "+id+" not found"));
        return new CategoryDTO(entity);
    }
    @Transactional
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        var category=new Category();
        BeanUtils.copyProperties(categoryDTO,category);// Ou categoryDTO.setName(categoryDTO.getName) com todos atributos a depender das sus estrategias.
        category=categoryRepository.save(category);
        return new CategoryDTO(category);
    }

}
