package com.spring.datafetcher.service;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.concurrent.Callable;

public class URLProcessorService implements Callable<Object> {
    private URL url;
    private Type type;

    public URLProcessorService(String url, Type type) throws Exception {
        this.url = new URL(url);
        this.type = type;
    }

    @Override
    public Object call() throws Exception {
        return new Gson().fromJson(readUrl(url), type);
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
