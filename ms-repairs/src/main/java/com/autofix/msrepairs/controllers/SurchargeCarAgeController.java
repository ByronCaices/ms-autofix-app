package com.autofix.msrepairs.controllers;

import com.autofix.msrepairs.entities.SurchargeCarAgeEntity;
import com.autofix.msrepairs.services.SurchargeCarAgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repairs/surchcarages")
@CrossOrigin("*")
public class SurchargeCarAgeController {
    @Autowired
    SurchargeCarAgeService surchargeCarAgeService;

    @GetMapping("/")
    public ResponseEntity<List<SurchargeCarAgeEntity>> listSurchargesCarAges() {
        List<SurchargeCarAgeEntity> surchargesCarAges = surchargeCarAgeService.listSurchargesCarAges();
        return ResponseEntity.ok(surchargesCarAges);
    }

    @GetMapping("/{category}/{bodywork}")
    public ResponseEntity<Float> getSurchargeByCategoryAndBodywork(@PathVariable String category,@PathVariable String bodywork) {
        Float surcharge = surchargeCarAgeService.getSurchargeByCategoryAndBodywork(category, bodywork);
        return ResponseEntity.ok(surcharge);
    }

}
