package com.autofix.mscar.controllers;
import com.autofix.mscar.entities.CarEntity;
import com.autofix.mscar.services.CarService;
import com.autofix.mscar.models.updtMileageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cars")
//@CrossOrigin("*")
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping("/")
    public ResponseEntity<List<CarEntity>> listCars() {
        List<CarEntity> cars = carService.getCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{plate}")
    public ResponseEntity<CarEntity> getCarByPlate(@PathVariable String plate) {
        CarEntity car = carService.getCarByPlate(plate);
        return ResponseEntity.ok(car);
    }

    @PostMapping("/updateMileage")
    public ResponseEntity<String> updateMileage(@RequestBody updtMileageModel updtMileage){
        String plate = updtMileage.getPlate();
        Long mileage = updtMileage.getMileage();
        carService.updateMileage(plate,mileage);
        return new ResponseEntity<>(
                "Mileage updated successfully!",
                HttpStatus.OK
        );
    }


    @PostMapping("/")
    public ResponseEntity<CarEntity> saveCar(@RequestBody CarEntity car){
        CarEntity carSaved = carService.saveCar(car);
        return ResponseEntity.ok(carSaved);
    }

    @PutMapping("/updt")
    public ResponseEntity<CarEntity> updateCar(@RequestBody CarEntity car){
        CarEntity carUpdated = carService.update(car);
        return ResponseEntity.ok(carUpdated);
    }

    @DeleteMapping("/{plate}")
    public ResponseEntity<Boolean> deleteCarByPlate(@PathVariable String plate) throws Exception {
        try {
            carService.deleteCar(plate);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }
    }


}