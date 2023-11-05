package com.codebooq.controller;

import com.codebooq.service.DemandHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demand-history")
public class DemandHistoryController {

    @Autowired
    private DemandHistoryService demandHistoryService;

    @GetMapping
    public Object getAllDemandHistory() {
        return demandHistoryService.getAllDemandHistory();
    }
}
