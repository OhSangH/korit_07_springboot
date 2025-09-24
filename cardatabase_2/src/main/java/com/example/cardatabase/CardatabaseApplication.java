package com.example.cardatabase;

import com.example.cardatabase.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

    // 생성자 주입 부분
    private final CarRepository repository;
    private final OwnerRepository ownerRepository;
        private final AppUserRepository userRepository;

    public CardatabaseApplication(CarRepository repository, OwnerRepository ownerRepository,  AppUserRepository userRepository) {
        this.repository = repository;
        this.ownerRepository = ownerRepository;
        this.userRepository = userRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(CardatabaseApplication.class, args);
    }


    // CommandLineRunner 의 추상 메서드인 run()을 구현
    @Override
    public void run(String... args) throws Exception {
        // 소유자 객체 추가
        Owner owner1 = new Owner("일", "김");
        Owner owner2 = new Owner("이", "강");
        ownerRepository.saveAll(Arrays.asList(owner1, owner2));

        // 그리고 car의 생성자에 field를 추가했기 때문에 오류 나는 것을 막기 위해 owner를 추가
        repository.save(new Car("Kia", "Seltos", "Chacol", "370SU5690", 2020, 30000000, owner1));
        repository.save(new Car("Hyundai", "Sonata", "White", "123456", 2025, 25000000, owner2));
        repository.save(new Car("Honda", "CR-V", "Black-White", "987654", 2024, 45000000, owner2));

        for (Car car : repository.findAll()) {
            logger.info("brand : {}, model : {}", car.getBrand(), car.getModel());
        }

        // AppUser 더미 데이터 추가
        userRepository.save(new AppUser("user", "$2a$12$I63QjoME9KM/ikFFfE7leeiouhGda0/P0uWWu.yl.xwoPc.DQ6o4e", "USER"));
        userRepository.save(new AppUser("admin", "$2a$12$oq7BIh9UY4j3cCreTQLYnudBGMRqVIKyybPCbmeTI.yQc4XNrXLIK", "ADMIN"));

    }
}
