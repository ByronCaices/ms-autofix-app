package com.autofix.msrepairs.controllers;

import com.autofix.msrepairs.entities.DiscountBonusEntity;
import com.autofix.msrepairs.services.DiscountBonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repairs/discbonus")
@CrossOrigin("*")
public class DiscountBonusController {
    @Autowired
    DiscountBonusService discountBonusService;

    @GetMapping("/")
    public ResponseEntity<List<DiscountBonusEntity>> getDiscountBonus() {
        List<DiscountBonusEntity> discountBonus = discountBonusService.getDiscountBonus();
        return ResponseEntity.ok(discountBonus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountBonusEntity> getDiscountBonusById(@PathVariable Long id) {
        DiscountBonusEntity discountBonus = discountBonusService.getDiscountBonusById(id);
        return ResponseEntity.ok(discountBonus);
    }

    @GetMapping("/stock/{brand}")
    public ResponseEntity<Integer> getStockByBrand(@PathVariable String brand) {
        Integer stock = discountBonusService.getStockByBrand(brand);
        return ResponseEntity.ok(stock);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscountBonusByBrand(@PathVariable Long id) {
        discountBonusService.deleteDiscountBonusByBrand(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity<DiscountBonusEntity> saveDiscountBonus(@RequestBody DiscountBonusEntity discountBonus){
        DiscountBonusEntity discountBonusSaved = discountBonusService.saveDiscountBonus(discountBonus);
        return ResponseEntity.ok(discountBonusSaved);
    }

    @PutMapping("/")
    public ResponseEntity<DiscountBonusEntity> updateDiscountBonus(@RequestBody DiscountBonusEntity discountBonus) {
        DiscountBonusEntity discountBonusUpdated = discountBonusService.saveDiscountBonus(discountBonus);
        return ResponseEntity.ok(discountBonusUpdated);
    }

}
