package com.example.cardatabase;

import com.example.cardatabase.domain.AppUser;
import com.example.cardatabase.domain.AppUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AppRepositoryTest {
    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    @DisplayName("유저확인")
    void findByUsernameShouldReturnAppUser() {
        AppUser appUser1 = new AppUser("user1","1234","USER");
        appUserRepository.save(appUser1);

        Optional<AppUser> appUsers = appUserRepository.findByUsername("user1");
        assertThat(appUsers).isPresent();
        assertThat(appUsers.get().getRole()).isEqualTo("USER");
    }
}
