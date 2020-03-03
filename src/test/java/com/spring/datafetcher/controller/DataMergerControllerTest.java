package com.spring.datafetcher.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.datafetcher.service.DataMergerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(DataMergerController.class)
public class DataMergerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DataMergerService dataMergerServ;

    static final String SERVICE_ENDPOINT = "http://127.0.0.1:8080/ProcessURL";

    @Test
    public void checkResponseURL() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(SERVICE_ENDPOINT)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}
