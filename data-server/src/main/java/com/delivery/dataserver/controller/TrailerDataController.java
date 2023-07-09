package com.delivery.dataserver.controller;

import com.delivery.dataserver.model.DataTrailer;
import com.delivery.dataserver.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TrailerDataController {
    DataService dataService;

    @Autowired
    public TrailerDataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    @RequestMapping("/{id}")
    public DataTrailer getConcreteFile(@PathVariable String id) {
        DataTrailer data = dataService.findDataById(Long.valueOf(id));
        return data;
    }
}
