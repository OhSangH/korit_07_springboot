package com.example.cardatabase4.service;

import com.example.cardatabase4.domain.Owner;
import com.example.cardatabase4.domain.OwnerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;

    // DONE : 전체 조회
    public List<Owner> getOwners() {
        return ownerRepository.findAll();
    }

    // DONE : id 조회
    public Optional<Owner> getOwnerById(Long id) {
        return ownerRepository.findById(id);
    }

    // DONE : OWNER 객체 추가
    public Owner addOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    // DONE : OWNER 객체 삭제
    public Boolean deleteOwner(Long id) {
        if (ownerRepository.existsById(id)) {
            ownerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // DONE : OWNER 객체 수정
    @Transactional
    public Optional<Owner> updateOwner(Long id, Owner ownerDetails) {
        return ownerRepository.findById(id)
                .map(owner -> {
                    owner.setFirstName(ownerDetails.getFirstName());
                    owner.setLastName(ownerDetails.getLastName());
                    return owner;
                });
    }
}
