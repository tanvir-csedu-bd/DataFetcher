package com.spring.datafetcher.service;

import com.google.gson.reflect.TypeToken;
import com.spring.datafetcher.model.Post;
import com.spring.datafetcher.model.User;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DataMergerServiceTest {

    @Test
    public void test_url_merger_service() throws Exception {
        User user = new DataMergerService().processAndMergeURLResponse();
        assertTrue(user.getId() > 0);
        assertTrue(user.getPosts().size() > 0);
    }
}
