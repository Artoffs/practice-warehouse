package com.example.practice;

import com.example.practice.dao.OrderItemRepository;
import com.example.practice.dao.OrderRepository;
import com.example.practice.entity.Order;
import com.example.practice.entity.OrderItem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class PracticeApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(PracticeApplication.class, args);

		OrderRepository orderRepository = run.getBean("orderRepository", OrderRepository.class);
		List<Order> all = orderRepository.findAll();
		System.out.println(all);

		OrderItemRepository orderItemRepository = run.getBean("orderItemRepository", OrderItemRepository.class);
		List<OrderItem> all1 = orderItemRepository.findAll();
		System.out.println(all1);
	}

}
