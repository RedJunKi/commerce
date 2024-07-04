package com.project.commerce;

import com.project.commerce.domain.item.Item;
import com.project.commerce.domain.item.ItemRepository;
import com.project.commerce.domain.user.Address;
import com.project.commerce.domain.user.User;
import com.project.commerce.domain.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@RequiredArgsConstructor
	@Component
	public static class postConstruct {

		private final UserRepository userRepository;
		private final ItemRepository itemRepository;

		@PostConstruct
		public void constructUserAndItem() {

			User user1 = User.builder().username("name1")
					.password("123")
					.email("email1")
					.address(new Address())
					.build();

			User user2 = User.builder().username("name2")
					.password("456")
					.email("email2")
					.address(new Address())
					.build();

			Item item1 = Item.builder()
					.name("빼빼로")
					.price(1000)
					.quantity(10)
					.manufacturer("삼성")
					.build();

			Item item2 = Item.builder()
					.name("빠삐코")
					.price(1000)
					.quantity(10)
					.manufacturer("엘지")
					.build();

			Item item3 = Item.builder()
					.name("물병")
					.price(3000)
					.quantity(20)
					.manufacturer("두산")
					.build();

			Item item4 = Item.builder()
					.name("커피")
					.price(5000)
					.quantity(30)
					.manufacturer("엔씨")
					.build();

			itemRepository.save(item1);
			itemRepository.save(item2);
			itemRepository.save(item3);
			itemRepository.save(item4);

			userRepository.save(user1);
			userRepository.save(user2);
		}

	}
}
