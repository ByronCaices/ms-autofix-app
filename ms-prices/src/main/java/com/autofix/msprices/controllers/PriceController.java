package com.autofix.msprices.controllers;

import com.autofix.msprices.entities.PriceEntity;
import com.autofix.msprices.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prices")
//@CrossOrigin("*")
public class PriceController {
    @Autowired
    PriceService priceService;

    @GetMapping("/")
    public ResponseEntity<List<PriceEntity>> getPrices(){
        List<PriceEntity> prices = priceService.getPrices();
        return ResponseEntity.ok(prices);
    }

    @GetMapping("/{engine}/{repair_type}")
    public ResponseEntity<Integer> getPriceByRepairTypeAndEngine(@PathVariable String engine,@PathVariable Integer repair_type){
        Integer price = priceService.getPriceByRepairTypeAndEngine(engine, repair_type);
        return ResponseEntity.ok(price);
    }

}