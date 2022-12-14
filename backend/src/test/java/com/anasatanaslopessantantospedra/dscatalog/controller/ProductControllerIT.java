package com.anasatanaslopessantantospedra.dscatalog.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductControllerIT {
    @Autowired
    private MockMvc mockMvc;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalProduct;


    @BeforeEach
    void setUp() throws Exception{
        existingId=1L;
        nonExistingId=1000L;
        countTotalProduct=12L;
    }
    @Test
    public void findAllShouldReturnSortedPageWhenSortByName()throws Exception{
       ResultActions result=
               mockMvc.perform(get("/products?page=0&size=12&sort=name,asc")
                       .accept(MediaType.APPLICATION_JSON));
       result.andExpect(status().isOk());
       result.andExpect(jsonPath("$.numberOfElements").value(countTotalProduct));
        result.andExpect(jsonPath("$.content").exists());
        result.andExpect(jsonPath("$.content[0].name").value("ATV1"));
        result.andExpect(jsonPath("$.content[1].name").value("BTV2"));
        result.andExpect(jsonPath("$.content[2].name").value("CTV3"));
    }



}
