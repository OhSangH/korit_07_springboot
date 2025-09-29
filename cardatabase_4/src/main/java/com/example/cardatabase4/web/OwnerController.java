package com.example.cardatabase4.web;

import com.example.cardatabase4.domain.Owner;
import com.example.cardatabase4.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping("/owners")
    public List<Owner> getOwners(){
        return ownerService.getOwners();
    }

    @GetMapping("/owners/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable Long id){
        return ownerService.getOwnerById(id)
                .map(owner -> ResponseEntity.ok().body(owner))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/owners")
    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner){
        Owner savedOwner = ownerService.addOwner(owner);
        return new ResponseEntity<>(savedOwner, HttpStatus.CREATED);
    }

    @DeleteMapping("/owners/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id){
        if(ownerService.deleteOwner(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/owners/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner owner){
        return ownerService.updateOwner(id,owner).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
