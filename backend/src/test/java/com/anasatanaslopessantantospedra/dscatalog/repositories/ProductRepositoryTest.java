package com.anasatanaslopessantantospedra.dscatalog.repositories;

import com.anasatanaslopessantantospedra.dscatalog.model.Product;
import com.anasatanaslopessantantospedra.dscatalog.repositories.productrepository.ProductRepository;
import com.anasatanaslopessantantospedra.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;
  private long existingId;
  private long notExistId;
  private long countTotalProducts;

  @BeforeEach
  void setUp() throws Exception{
     existingId = 1L;
     notExistId=1000L;
     countTotalProducts=11L;
  }
  @Test
  public void retunEmptyOptinalWhenIdNotExist(){
    Optional<Product>result=productRepository.findById(notExistId);
    Assertions.assertTrue(result.isEmpty());
  }
  @Test
  public void retunNotEmptyOptinalWhenIdExist(){
    Optional<Product>result=productRepository.findById(existingId);
    Assertions.assertTrue(result.isPresent());
  }

  @Test
  public void saveShouldPersistWithAutoincrementWhenIdNull(){
    Product product= Factory.createProduct();
    product.setId(null);
    product=productRepository.save(product);
    Assertions.assertNotNull(product.getId());
    //Assertions.assertEquals(countTotalProducts + 1,product.getId());

  }

  @Test
    public void deleteShouldDeleteObjectWhenIdExists(){
    productRepository.deleteById(existingId);
    Optional<Product>result=productRepository.findById(existingId);
    Assertions.assertFalse(result.isPresent());
  }


  @Test
  public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdIsNotExist(){
    Assertions.assertThrows(EmptyResultDataAccessException.class,()->{
      productRepository.deleteById(notExistId);
    });
  }






}
