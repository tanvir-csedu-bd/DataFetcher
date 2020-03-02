package com.spring.datafetcher.service;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class DataMergerServiceTest {

    static final String URL_1 = "http://jsonplaceholder.typicode.com/users/1";
    static final String URL_2 = "http://jsonplaceholder.typicode.com/posts?userId=1";

    @Test
    public void test_with_one_url() throws Exception {
        Map<String, Object> response = new DataMergerService().processAndMergeURLResponse(new String[]{URL_1});
        assertTrue(response.size() > 0);
    }

    @Test
    public void test_with_two_url() throws Exception {
        Map<String, Object> response_1 = new DataMergerService().processAndMergeURLResponse(new String[]{URL_1});
        Map<String, Object> response_2 = new DataMergerService().processAndMergeURLResponse(new String[]{URL_2});
        Map<String, Object> responseMerge = new HashMap<>();
        responseMerge.putAll(response_1);
        responseMerge.putAll(response_2);

        Map<String, Object> responseOfTwoURL = new DataMergerService().processAndMergeURLResponse(new String[]{URL_1, URL_2});
        assertTrue(responseOfTwoURL.size() == responseMerge.size());
    }

    @Test
    public void test_with_multiple_urls() throws Exception {
        String[] urls = {URL_1, URL_2, "http://api.plos.org/search?q=title:DNA", "http://api.plos.org/search?q=title:RNA", "http://api.plos.org/search?q=title:COMPUTER"};
        Map<String, Object> responseMerge = new HashMap<>();
        for (String URL : urls) {
            responseMerge.putAll(new DataMergerService().processAndMergeURLResponse(new String[]{URL}));
        }

        Map<String, Object> responseOfMultipleURLs = new DataMergerService().processAndMergeURLResponse(urls);
        assertTrue(responseOfMultipleURLs.size() == responseMerge.size());
    }
}
