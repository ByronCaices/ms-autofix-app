package com.autofix.msrepairs.clients;

import com.autofix.msrepairs.models.CarEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@FeignClient(value = "ms-cars",
        path = "/cars"
)
public interface CarsFeignClient {
    @GetMapping("/")
    public List<CarEntity> listCars();

    @GetMapping("/{plate}")
    public CarEntity getCarByPlate(@PathVariable String plate);


    @PostMapping("/")
    public CarEntity saveCar(@RequestBody CarEntity car);

    @PutMapping("/updt")
    public CarEntity updateCar(@RequestBody CarEntity car);

    @DeleteMapping("/{plate}")
    public Boolean deleteCarByPlate(@PathVariable String plate);
}
