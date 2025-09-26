package com.example.cardatabase;

import com.example.cardatabase.domain.Car;
import com.example.cardatabase.domain.CarRepository;
import com.example.cardatabase.domain.Owner;
import com.example.cardatabase.domain.OwnerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    @DisplayName("차량 저장 메서드 테스트")
    void saveCar() {
        //given - 제반 준비 사항
        // Car Entity를 확인해봤을 때 field로 Owner를 요구
        Owner owner = new Owner("Gemini","GPT");
        ownerRepository.save(owner);
        Car car = new Car("Ford", "Mustang", "Red", "ABCDEF", 2021, 567890, owner);
        // when - 테스트 실행
        // 저장이 됬는가 확인
        carRepository.save(car);

        // then - 그 결과가 어떠할지
        assertThat(carRepository.findById(car.getId())).isPresent();

        assertThat(carRepository.findById(car.getId()).get().getBrand()).isEqualTo("Ford");
    }

    @Test
    @DisplayName("차량삭제테스트")
    void deleteCar() {
        Owner owner = new Owner("Gemini","GPT");
        ownerRepository.save(owner);
        Car car = new Car("Ford", "Mustang", "Red", "ABCDEF", 2021, 567890, owner);
        carRepository.save(car);

        carRepository.deleteById(car.getId());
        assertThat(carRepository.count()).isEqualTo(3);
    }

    @Test
    @DisplayName("차량조회테스트")
    void findByBrandShouldReturnCar() {
        Owner owner = new Owner("Gemini","GPT");
        ownerRepository.save(owner);
        Car car = new Car("Ford", "Mustang", "Red", "ABCDEF", 2021, 567890, owner);
        Car car1 = new Car("Kia", "k5", "Black", "AAASSSS", 2024, 567390, owner);
        Car car2 = new Car("Kia", "k7", "White", "AASDEF", 2022, 563890, owner);
        carRepository.save(car);
        carRepository.save(car1);
        carRepository.save(car2);

        List<Car> cars = carRepository.findByBrand("Kia");
        assertThat(cars.size()).isEqualTo(3);
    }
}

