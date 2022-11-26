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
        Category category4=new Category(null,"Books3", Instant.now(),Instant.now());
        Category category5=new Category(null,"Electronics5",Instant.now(),Instant.now() );
        Category category6=new Category(null,"Computers10",Instant.now(),Instant.now() );
        Category category7=new Category(null,"Books56", Instant.now(),Instant.now());
        Category category8=new Category(null,"Electronicxxs",Instant.now(),Instant.now() );
        Category category9=new Category(null,"Computers3x",Instant.now(),Instant.now() );
        Category category10=new Category(null,"Books963", Instant.now(),Instant.now());
        Category category11=new Category(null,"Electron15ics",Instant.now(),Instant.now() );
        Category category12=new Category(null,"Computers321",Instant.now(),Instant.now() );
        Category category13=new Category(null,"Books45", Instant.now(),Instant.now());
        Category category14=new Category(null,"Electro3254nics",Instant.now(),Instant.now() );
        Category category15=new Category(null,"Computer4789s",Instant.now(),Instant.now() );
        Category category16=new Category(null,"Book78s", Instant.now(),Instant.now());
        Category category17=new Category(null,"Electr78onics",Instant.now(),Instant.now() );
        Category category18=new Category(null,"Computer456s",Instant.now(),Instant.now() );


        categoryRepository.saveAll(Arrays.asList(category1,category2,category3,category4,category5,category6,category7,category8,category9,
                category10,category11,category12,category13,category14,category15,category16,category17,category18));


    }
}
