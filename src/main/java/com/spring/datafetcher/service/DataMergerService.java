package com.spring.datafetcher.service;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class DataMergerService {
    /**
     * Process the URLs asynchronously and returns a Map which contains the merged response of all the URL
     * <p>
     * If URL is invalid then it throws an exception
     *
     * @param urls which needs to processed
     * @return a Map contains the merged response of the URLs
     * @throws NullPointerException if urls are null or invalid
     */
    public Map<String, Object> processAndMergeURLResponse(String[] urls) throws Exception {
        Map<String, Object> urlResponse = new HashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(urls.length);
        List<Future<Map<String, Object>>> futures = new ArrayList<>(urls.length);
        for (String url : urls) {
            futures.add(executorService.submit(new URLProcessorService(url)));
        }
        for (Future<Map<String, Object>> future : futures) {
            urlResponse.putAll(future.get(20, TimeUnit.SECONDS));
        }
        executorService.shutdown();
        return urlResponse;
    }
}
