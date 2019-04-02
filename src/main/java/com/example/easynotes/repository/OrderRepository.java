package com.example.easynotes.repository;

import com.example.easynotes.model.Book;
import com.example.easynotes.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
