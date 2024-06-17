package com.autofix.msreports.controllers;

import com.autofix.msreports.services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportsController {
    @Autowired
    ReportsService reportsService;

    @GetMapping("/repairTypeAmounts")
    public List<Object[]> getRepairTypeAmounts() {
        return reportsService.getRepairTypeAmounts();
    }

}
