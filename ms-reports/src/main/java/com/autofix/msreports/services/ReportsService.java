package com.autofix.msreports.services;

import com.autofix.msreports.clients.RepairsFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsService {

    @Autowired
    RepairsFeignClient repairsFeignClient;

    public List<Object[]> getRepairTypeAmounts() {
        return repairsFeignClient.getRepairTypeAmounts();
    }

    public List<Object[]> getMonthlyRepairReport(String month) {
        return repairsFeignClient.getMonthlyRepairReport(month);
    }
}
