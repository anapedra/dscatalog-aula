package com.anasatanaslopessantantospedra.dscatalog.service;

import com.anasatanaslopessantantospedra.dscatalog.DTO.ProductDTO;
import com.anasatanaslopessantantospedra.dscatalog.repositories.productrepository.ProductRepository;
import com.anasatanaslopessantantospedra.dscatalog.service.exceptions.ResorceNotFoundException;
import com.anasatanaslopessantantospedra.dscatalog.service.produtctservice.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
@Transactional
public class ProductServeceIT {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    private Long existengId;
    private Long nonExistingId;
    private Long countTotalProduct;


    @BeforeEach
    void setUp() throws Exception{
      existengId=1L;
      nonExistingId=1000L;
      countTotalProduct=12L;
    }
    @Test
    public void deleteShouldDeleteResourceWhenIdExist(){
       productService.deletProduct(existengId);
        Assertions.assertEquals(countTotalProduct-1,productRepository.count());
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist(){
        Assertions.assertThrows(ResorceNotFoundException.class,()->{
           productService.deletProduct(nonExistingId);
        });

    }
    @Test
    public void findAllPageShouldReturnPageWhenPage0Size10(){
        PageRequest pageRequest=PageRequest.of(0,10);
        Page<ProductDTO> result=productService.findAllProductPaged(pageRequest);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(0,result.getNumber());
        Assertions.assertEquals(10,result.getSize());
        Assertions.assertEquals(countTotalProduct,result.getTotalElements());
        Assertions.assertEquals(result.getSize()+2,result.getTotalElements());
    }
    @Test
    public void findAllPageShouldReturnPageEmptyWhenDoesNotExist(){
        PageRequest pageRequest=PageRequest.of(50,10);
        Page<ProductDTO> result=productService.findAllProductPaged(pageRequest);
        Assertions.assertTrue(result.isEmpty());

    }
    @Test
    public void findAllPageShouldReturnSortPageWhen(){
        PageRequest pageRequest=PageRequest.of(0,10, Sort.by("name"));
        Page<ProductDTO> result=productService.findAllProductPaged(pageRequest);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals("ATV1",result.getContent().get(0).getName());
        Assertions.assertEquals("BTV2",result.getContent().get(1).getName());
        Assertions.assertEquals("CTV3",result.getContent().get(2).getName());
    }
}
