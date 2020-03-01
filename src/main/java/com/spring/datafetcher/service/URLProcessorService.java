package com.spring.datafetcher.service;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;

public class URLProcessorService implements Callable<Map<String, Object>> {
    private static final Logger log = LoggerFactory.getLogger(URLProcessorService.class);

    private final URL url;

    public URLProcessorService(String url) throws Exception {
        this.url = new URL(url);
    }

    @Override
    public Map<String, Object> call() throws Exception {
        Map<String, Object> response = null;
        int attempt = 1;
        while (response == null && attempt++ < 3) {
            try {
                String jsonStr = readUrl(url);
                try {
                    response = new Gson().fromJson(jsonStr, new TypeToken<Map<String, Object>>() {
                    }.getType());
                } catch (JsonSyntaxException ex) {
                    log.error("Unable to parse data of " + url.toString() + " because of " + ex + ". Trying to generalize the parsing");
                    response = new HashMap<>();
                    response.put("CustomKey", new Gson().fromJson(jsonStr, Object.class));
                }
            } catch (IOException e) {
                log.error("Trying to fetch data ", e);
                Thread.sleep(5000);
            }
        }
        return response;
    }

    private String readUrl(URL url) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            StringBuilder builder = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                builder.append(chars, 0, read);
            return builder.toString();
        }
    }
}
