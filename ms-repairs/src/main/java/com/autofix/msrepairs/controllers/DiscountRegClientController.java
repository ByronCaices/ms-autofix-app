package com.autofix.msrepairs.controllers;

import com.autofix.msrepairs.entities.DiscountRegClientEntity;
import com.autofix.msrepairs.services.DiscountRegClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repairs/discregclients")
@CrossOrigin("*")
public class DiscountRegClientController {
    @Autowired
    DiscountRegClientService discountRegClientService;

    @GetMapping("/")
    public ResponseEntity<List<DiscountRegClientEntity>> listDiscountRegClients() {
        List<DiscountRegClientEntity> discountRegClients = discountRegClientService.getDiscountRegClients();
        return ResponseEntity.ok(discountRegClients);
    }

    @GetMapping("/{category}/{engine}")
    public ResponseEntity<Float> getDiscountByCategoryAndEngine(@PathVariable String category,@PathVariable String engine) {
        Float discount = discountRegClientService.getDiscountByCategoryAndEngine(category, engine);
        return ResponseEntity.ok(discount);
    }

}
