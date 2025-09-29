package com.example.cardatabase4.service;

import com.example.cardatabase4.domain.Car;
import com.example.cardatabase4.domain.CarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<Car> getCars(){
        return carRepository.findAll();
    }

    //새로운 자동차 저장
    public Car addCar(Car car){
        return carRepository.save(car);
    }

    public Optional<Car> getCarById(Long id){
        return carRepository.findById(id);
    }

    public boolean deleteCar(Long id){
        if(carRepository.existsById(id)){
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<Car> updateCar(Long id , Car carDetails){
        return carRepository.findById(id)
                .map(car -> {
                    car.setBrand(carDetails.getBrand());
                    car.setModel(carDetails.getModel());
                    car.setPrice(carDetails.getPrice());
                    car.setColor(carDetails.getColor());
                    car.setModelYear(carDetails.getModelYear());
                    car.setRegistrationNumber(carDetails.getRegistrationNumber());
                    return car;
                    //carRepository.save(car);가 아닙니다.
                    //@Transactional로 인해 수정점이 발견되면 자동으로 변경된다.
                });
    }
}
