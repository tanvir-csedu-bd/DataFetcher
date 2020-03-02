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
    static final String[] URLS = {"http://jsonplaceholder.typicode.com/users/1", "http://jsonplaceholder.typicode.com/posts?userId=1"};

    private String toJSON(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

    @Test
    public void checkResponseURL() throws Exception {
        String inputJson = toJSON(URLS);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(SERVICE_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void invalidResponseFormatCheck() throws Exception {
        String inputJson = toJSON(URLS);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(SERVICE_ENDPOINT).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.BAD_REQUEST.value(), status);
    }

    @Test
    public void invalidURLCheck() throws Exception {
        String inputJson = toJSON(new String[]{"http://invalid-url", URLS[0]});
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(SERVICE_ENDPOINT).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.BAD_REQUEST.value(), status);
    }
}
