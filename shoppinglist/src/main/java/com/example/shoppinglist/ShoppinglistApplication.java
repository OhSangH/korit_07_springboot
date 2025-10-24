package com.example.shoppinglist;

import com.example.shoppinglist.domain.Item;
import com.example.shoppinglist.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ShoppinglistApplication implements CommandLineRunner {
    private final ItemRepository itemRepository;


	public static void main(String[] args) {
		SpringApplication.run(ShoppinglistApplication.class, args);
	}


    @Override
    public void run(String... args) throws Exception {
        Item i1 = new Item("제품 1",10);
        Item i2 = new Item("제품 2",20);
        Item i3 = new Item("제품 3",3);
        Item i4 = new Item("제품 4",70);

        itemRepository.save(i1);
        itemRepository.save(i2);
        itemRepository.save(i3);
        itemRepository.save(i4);
    }
}
