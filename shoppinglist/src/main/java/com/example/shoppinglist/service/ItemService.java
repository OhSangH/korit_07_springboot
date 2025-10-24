//package com.example.shoppinglist.service;
//
//import com.example.shoppinglist.domain.Item;
//import com.example.shoppinglist.repository.ItemRepository;
//import com.example.shoppinglist.dto.ItemRecord;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.*;
//
//@Service
//@RequiredArgsConstructor
//public class ItemService {
//    private final ItemRepository itemRepository;
//
//    @Transactional(readOnly = true)
//    public List<Item> getItems() {
//        return itemRepository.findAll();
//    }
//
//    @Transactional(readOnly = true)
//    public Item getItemsById(Long id) {
//        return itemRepository.findById(id).get();
//    }
//
//    @Transactional
//    public Item createItem(ItemRecord itemRecord) {
//        Item newItem = new Item(itemRecord.product(), itemRecord.amount());
//
//        return itemRepository.save(newItem);
//    }
//
//    @Transactional
//    public void deleteItem(Long id) {
//        itemRepository.deleteById(id);
//    }
//
//    @Transactional
//    public Item updateItem(Long id, ItemRecord itemRecord) {
//        Item item = itemRepository.findById(id).isPresent() ? itemRepository.findById(id).get() : null;
//        if (item == null) {
//            return null;
//        }
//        item.setProduct(itemRecord.product());
//        item.setAmount(itemRecord.amount());
//
//        return itemRepository.save(item);
//    }
//
//}
