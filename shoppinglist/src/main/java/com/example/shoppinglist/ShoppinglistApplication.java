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
        Item i1 = new Item("1",4);
        Item i2 = new Item("2",4);
        Item i3 = new Item("3",4);
        Item i4 = new Item("4",4);

        itemRepository.save(i1);
        itemRepository.save(i2);
        itemRepository.save(i3);
        itemRepository.save(i4);
    }
}
