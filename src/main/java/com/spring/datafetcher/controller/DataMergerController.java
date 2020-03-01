package com.spring.datafetcher.controller;

import com.spring.datafetcher.service.DataMergerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataMergerController {

    @Autowired
    private DataMergerService dataMergerServ;

    @PostMapping("/ProcessURL")
    @ResponseBody
    public Object processURL(@RequestBody String[] urls) throws Exception {
        return dataMergerServ.processAndMergeURLResponse(urls);
    }
}
