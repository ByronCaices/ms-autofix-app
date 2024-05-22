package com.autofix.msrepairs.clients;

import com.autofix.msrepairs.models.PriceEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "ms-prices",
        path = "/prices"
)
public interface PricesFeignClient {
    @GetMapping("/")
    public List<PriceEntity> getPrices();

    @GetMapping("/{engine}/{repair_type}")
    public Integer getPriceByRepairTypeAndEngine(@PathVariable String engine, @PathVariable Integer repair_type);
}
