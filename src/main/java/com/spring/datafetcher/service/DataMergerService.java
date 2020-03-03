package com.spring.datafetcher.service;


import com.google.gson.reflect.TypeToken;
import com.spring.datafetcher.model.Post;
import com.spring.datafetcher.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class DataMergerService {

    final static String URL_1 = "http://jsonplaceholder.typicode.com/users/1";
    final static String URL_2 = "http://jsonplaceholder.typicode.com/posts?userId=1";

    /**
     * Process the URLs asynchronously and returns User object which contains the merged response of all the URLs
     * <p>
     * If URL is invalid then it throws an exception
     *
     * @return a User object contains the merged response of the URLs
     * @throws java.util.concurrent.TimeoutException if unable to process a URL within 20 seconds
     */
    public User processAndMergeURLResponse() throws Exception {
        User user = new User();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<Object>> futures = new ArrayList<>(2);
        futures.add(executorService.submit(new URLProcessorService(URL_1, new TypeToken<User>() {
        }.getType())));
        futures.add(executorService.submit(new URLProcessorService(URL_2, new TypeToken<ArrayList<Post>>() {
        }.getType())));

        for (Future<Object> future : futures) {
            Object obj = future.get(20, TimeUnit.SECONDS);
            if (obj.getClass() == User.class) {
                List<Post> posts = user.getPosts();
                user = (User) obj;
                user.setPosts(posts);
            } else {
                user.setPosts((List<Post>) obj);
            }
        }
        user.updateChildObject();
        executorService.shutdown();
        return user;
    }
}
