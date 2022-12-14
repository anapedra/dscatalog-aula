package com.anasatanaslopessantantospedra.dscatalog.service;

import com.anasatanaslopessantantospedra.dscatalog.DTO.ProductDTO;
import com.anasatanaslopessantantospedra.dscatalog.model.Product;
import com.anasatanaslopessantantospedra.dscatalog.repositories.productrepository.ProductRepository;
import com.anasatanaslopessantantospedra.dscatalog.service.exceptions.DataBaseException;
import com.anasatanaslopessantantospedra.dscatalog.service.exceptions.ResorceNotFoundException;
import com.anasatanaslopessantantospedra.dscatalog.service.produtctservice.ProductService;
import com.anasatanaslopessantantospedra.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    private PageImpl<Product> page;
    private long existId;
    private long  notExistId=1000L;
    private long dependentId;
    private Product product;
    
    @BeforeEach
    void setUp() throws Exception{
        existId = 1L;
        notExistId=2;
        dependentId=3L;
        product= Factory.createProduct();
        page=new PageImpl<>(List.of(product));

        Mockito.when(productRepository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);
        Mockito.when(productRepository.save(ArgumentMatchers.any())).thenReturn(product);

        Mockito.when(productRepository.findById(existId)).thenReturn(Optional.of(product));
        Mockito.when(productRepository.findById(notExistId)).thenReturn(Optional.empty());


        Mockito.when(productRepository.getOne(existId)).thenReturn(product);
        Mockito.doThrow(ResorceNotFoundException.class).when(productRepository).getOne(notExistId);

        Mockito.doNothing().when(productRepository).deleteById(existId);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(productRepository).deleteById(notExistId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(productRepository).deleteById(dependentId);

    }
    @Test
    public void updateShouldReturnReturnResourceNotFoundExceptionWhenIdNotExist(){
                Assertions.assertThrows(ResorceNotFoundException.class ,()->{
                                       productService.upDateProduct(notExistId,new ProductDTO());
                  });
                              Mockito.verify(productRepository,Mockito.times(1)).getOne(notExistId);
    }
    @Test
    public void updateShouldReturnObjectWhenIdExist(){
         ProductDTO result=productService.upDateProduct(existId,new ProductDTO());
         Assertions.assertNotNull(result);
         Mockito.verify(productRepository,Mockito.times(1)).save(product);

    }
    @Test
    public void findByIdShouldReturnResourceNotFoundExceptionWhenIdNotExist(){
         Assertions.assertThrows(ResorceNotFoundException.class ,()->{
                          productService.findProductyById(notExistId);
              });
               Mockito.verify(productRepository,Mockito.times(1)).findById(notExistId);
    }
    @Test
    public void findByIdShouldReturnObjectWhenIdExist(){
     ProductDTO result=productService.findProductyById(existId);
     Assertions.assertNotNull(result);
     Mockito.verify(productRepository,Mockito.times(1)).findById(existId);

    }
    @Test
    public void findAllPageShouldReturnPage(){
        Pageable pageable= PageRequest.of(0,10);
        Page<ProductDTO> result=productService.findAllProductPaged(pageable);
        Assertions.assertNotNull(result);
        Mockito.verify(productRepository,Mockito.times(1)).findAll(pageable);
        
    }
    @Test
     public void deleteShouldThrowDataBaseExceptionWhenIdWhenDependitId(){
     Assertions.assertThrows(DataBaseException.class,()->{
      productService.deletProduct(dependentId);
      });
             Mockito.verify(productRepository,Mockito.times(1)).deleteById(dependentId);
    }

    @Test
    public void  deleteShouldThrowResourceNotFoundExceptionWhenIdDoesExist(){
             Assertions.assertThrows(ResorceNotFoundException.class,()->{
                 productService.deletProduct(notExistId);
             });
             Mockito.verify(productRepository,Mockito.times(1)).deleteById(notExistId);

    }

    @Test
    public void deleteShouldDoNotThingWhenIdExist(){
        Assertions.assertDoesNotThrow(()->{
            productService.deletProduct(existId);
        });
      Mockito.verify(productRepository,Mockito.times(1)).deleteById(existId);

    }


}
