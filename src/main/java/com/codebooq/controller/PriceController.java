package com.codebooq.controller;

import com.codebooq.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/price")
@CrossOrigin(origins = "*")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping
    public Map<String, Double> getPrices() {
        return priceService.calculatePrices();
    }


}
