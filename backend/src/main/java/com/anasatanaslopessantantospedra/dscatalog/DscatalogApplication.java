package com.anasatanaslopessantantospedra.dscatalog;

import com.anasatanaslopessantantospedra.dscatalog.model.Category;
import com.anasatanaslopessantantospedra.dscatalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DscatalogApplication implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    public DscatalogApplication(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DscatalogApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Category category1=new Category(null,"Books", Instant.now(),Instant.now());
        Category category2=new Category(null,"Electronics",Instant.now(),Instant.now() );
        Category category3=new Category(null,"Computers",Instant.now(),Instant.now() );

        categoryRepository.saveAll(Arrays.asList(category1,category2,category3));


    }
}
