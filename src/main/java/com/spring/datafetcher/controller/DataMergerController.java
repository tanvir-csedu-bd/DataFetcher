package com.spring.datafetcher.controller;

import com.spring.datafetcher.service.DataMergerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DataMergerController {

    @Autowired
    private DataMergerService dataMergerServ;

    @GetMapping("/ProcessURL")
    @ResponseBody
    public Object processURL() throws Exception {
        return dataMergerServ.processAndMergeURLResponse();
    }
}
