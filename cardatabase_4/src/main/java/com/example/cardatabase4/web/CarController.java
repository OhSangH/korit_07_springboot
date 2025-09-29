package com.example.cardatabase4.web;

import com.example.cardatabase4.domain.Car;
import com.example.cardatabase4.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    // 1. 모든 차량 정보 조회
    @GetMapping("/cars")
    public List<Car> getCars(){
        return carService.getCars();
    }

    // 2. 차량 한대 추가
    @PostMapping("/cars")
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        Car savedCar = carService.addCar(car);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    // 3. 차량 한대 조회
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id){
        return carService.getCarById(id)
                .map(car -> ResponseEntity.ok().body(car))
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. 차량 한대 삭제
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        if (carService.deleteCar(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // 5. 차량 수정
    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        return carService.updateCar(id,carDetails)
                .map(car -> ResponseEntity.ok().body(car))
                .orElse(ResponseEntity.notFound().build());
    }

}