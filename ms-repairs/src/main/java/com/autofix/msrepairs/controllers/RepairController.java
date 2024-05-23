package com.autofix.msrepairs.controllers;


import com.autofix.msrepairs.entities.RepairEntity;
import com.autofix.msrepairs.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repairs")
@CrossOrigin("*")
public class RepairController {
    @Autowired
    RepairService repairService;

    @GetMapping("/")
    public ResponseEntity<List<RepairEntity>> listRepairs() {
        List<RepairEntity> repairs = repairService.getRepairs();
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairEntity> getRepairById(@PathVariable Long id) {
        RepairEntity repair = repairService.getById(id);
        return ResponseEntity.ok(repair);
    }

    @GetMapping("/code_{repair_code}")
    public ResponseEntity<List<RepairEntity>> getRepairsByRepairCode(@PathVariable String repair_code) {
        List<RepairEntity> repairs = repairService.getRepairsByRepairCode(repair_code);
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/totalAmount_{repair_code}")
    public ResponseEntity<Float> sumTotalAmountByRepairCode(@PathVariable String repair_code) {
        float totalAmount = repairService.sumTotalAmountByRepairCode(repair_code);
        return ResponseEntity.ok(totalAmount);
    }

    @GetMapping("/repairTypeAmounts")
    public ResponseEntity<List<Object[]>> getRepairTypeAmounts() {
        List<Object[]> repairTypeAmounts = repairService.getRepairTypeAmounts();
        return ResponseEntity.ok(repairTypeAmounts);
    }

    @GetMapping("/repairTypeAmountsByEngine")
    public ResponseEntity<List<Object[]>> getRepairTypeAmountsByEngine() {
        List<Object[]> repairTypeAmounts = repairService.getRepairTypeAmountsByEngine();
        return ResponseEntity.ok(repairTypeAmounts);
    }

    @GetMapping("/averageRepairTimeByBrand")
    public ResponseEntity<List<Object[]>> getAverageRepairTimeByBrand() {
        List<Object[]> averageRepairTimeByBrand = repairService.getAverageRepairTimeByBrand();
        return ResponseEntity.ok(averageRepairTimeByBrand);
    }

    @PostMapping("/")
    public ResponseEntity<RepairEntity> saveRepair(@RequestBody RepairEntity repair){
        RepairEntity repairUpdated = repairService.saveRepair(repair); //Agrega los datos faltantes al repair sobre precios y descuentos
        return ResponseEntity.ok(repairUpdated);
    }

    @PutMapping("/")
    public ResponseEntity<RepairEntity> updateRepair(@RequestBody RepairEntity repair){
        RepairEntity repairUpdated = repairService.updateRepair(repair);
        return ResponseEntity.ok(repairUpdated);
    }

    @PutMapping("/calcFinalPrice/{id}")
    public ResponseEntity<RepairEntity> updateRepair(@PathVariable Long id){
        RepairEntity repairUpdated = repairService.addSurchPickupDelay(id);
        return ResponseEntity.ok(repairUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRepair(@PathVariable Long id){
        try {
            repairService.deleteRepair(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }
}
