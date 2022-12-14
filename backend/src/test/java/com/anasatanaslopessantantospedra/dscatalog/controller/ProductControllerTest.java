package com.anasatanaslopessantantospedra.dscatalog.controller;

import com.anasatanaslopessantantospedra.dscatalog.DTO.ProductDTO;
import com.anasatanaslopessantantospedra.dscatalog.service.produtctservice.ProductService;
import com.anasatanaslopessantantospedra.dscatalog.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(ProductControllerTest.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    private ProductDTO productDTO;
    private PageImpl<ProductDTO>page;

    @BeforeEach
    void setUp() throws Exception{
        productDTO= Factory.createProductDTO();
        page=new PageImpl<>(List.of(productDTO));
        when(productService.findAllProductPaged(any())).thenReturn(page);
    }
    @Test
    public void findAllShouldReturnPage(){

    }
}
