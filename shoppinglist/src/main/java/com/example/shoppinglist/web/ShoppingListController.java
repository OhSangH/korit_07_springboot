package com.example.shoppinglist.web;

import com.example.shoppinglist.domain.Item;
import com.example.shoppinglist.dto.ItemRecord;
import com.example.shoppinglist.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ShoppingListController {
    private final ItemService itemService ;

    @GetMapping
    public ResponseEntity<List<Item>> getItems() {
        return ResponseEntity.ok(itemService.getItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> findItemById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItemsById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody ItemRecord itemRecord) {
        return ResponseEntity.ok(itemService.updateItem(id, itemRecord));
    }

    @PostMapping
    public ResponseEntity<Item> CreateItem(@RequestBody ItemRecord itemrecord) {
        Item createItem = itemService.createItem(itemrecord);
        return  new ResponseEntity<>(createItem, HttpStatus.CREATED);
    }
}
