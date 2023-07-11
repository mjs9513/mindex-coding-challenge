package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ReportingController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingController.class);

    @Autowired
    private ReportingService reportingService;

    @GetMapping("/reportingstructure/{id}")
    public ReportingStructure create(@PathVariable String id)
    {
        LOG.debug("Received employee reporting structure create request for id [{}]", id);
        //return null;
        return reportingService.create(id);
    }
}