package com.autofix.msreports.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ms-repairs", path = "/repairs")
public interface RepairsFeignClient {

    @GetMapping("/repairTypeAmounts")
    List<Object[]> getRepairTypeAmounts();

    @GetMapping("/MonthlyReport/{month}")
    List<Object[]> getMonthlyRepairReport(@PathVariable String month);

}
