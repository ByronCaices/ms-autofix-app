package com.autofix.msreports.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ms-repairs", path = "/repairs")
public interface RepairsFeignClient {

    @GetMapping("/Report1")
    List<Object[]> getReport1();

    @GetMapping("/Report2")
    List<Object[]> getReport2(@RequestParam int year, @RequestParam int month);

    @GetMapping("/repairTypeAmounts")
    List<Object[]> getRepairTypeAmounts();

}
