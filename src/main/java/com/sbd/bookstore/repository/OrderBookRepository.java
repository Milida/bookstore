package com.sbd.bookstore.repository;

import com.sbd.model.Order;
import com.sbd.model.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {
    List<OrderBook> findByOrder(Order order);
}
