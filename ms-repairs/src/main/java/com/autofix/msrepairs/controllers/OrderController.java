package com.autofix.msrepairs.controllers;

import com.autofix.msrepairs.entities.OrderEntity;
import com.autofix.msrepairs.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repairs/orders")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/bonus/{repairCode}")
    public ResponseEntity<Float> getBonusByRepairCodee(@PathVariable String repairCode) {
        float totalAmount = orderService.getBonusByRepairCode(repairCode);
        return ResponseEntity.ok(totalAmount);
    }

    @PostMapping("/{id}")
    public ResponseEntity<OrderEntity> saveOrder(@PathVariable Long id){
        OrderEntity orderSaved = orderService.closeRepairOrder(id);
        return ResponseEntity.ok(orderSaved);
    }
}
