package com.sbd.controller;

import com.sbd.bookstore.repository.*;
import com.sbd.model.Book;
import com.sbd.model.Order;
import com.sbd.model.OrderBook;
import com.sbd.model.User;
import com.sbd.model.embedded.OrderBookId;
import com.sbd.payroll.BadRequestException;
import com.sbd.payroll.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderBookRepository orderBookRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PackingRepository packingRepository;

    @GetMapping
    ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Order with ID = %d was not found", id)));
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/price")
    ResponseEntity<BigDecimal> getPrice(@RequestBody Order orderBody) {

        Optional<User> user = userRepository.findById(orderBody.getUser().getId());
        user.ifPresent(orderBody::setUser);
        orderBody.setPrice(orderBody.getPrice());

        return new ResponseEntity<>(orderBody.getPrice(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Order> addOrder(@RequestBody Order orderBody) {
        if (orderBody.getPayment().getId() < 1)
            throw new BadRequestException("Please select payment type");

        if (orderBody.getShipment().getId() < 1)
            throw new BadRequestException("Please select shipment type");

        for (OrderBook orderBook : orderBody.getOrderBook()) {
            Book book = bookRepository.findById(orderBook.getBook().getId()).orElseThrow(() -> new NotFoundException(
                    String.format("Book with ID = %d was not found!", orderBook.getBook().getId())));
            if (book.getQuantity() < orderBook.getQuantity()) {
                throw new BadRequestException(
                        String.format("Maxiumum number of '%s' available is %d", book.getTitle(), book.getQuantity()));
            }
        }

        for (OrderBook orderBook : orderBody.getOrderBook()) {
            Book book = bookRepository.findById(orderBook.getBook().getId()).get();
            book.setQuantity(book.getQuantity() - orderBook.getQuantity());
            bookRepository.save(book);
        }

        Optional<User> user = userRepository.findById(orderBody.getUser().getId());
        user.ifPresent(orderBody::setUser);

        Order order = orderRepository.save(orderBody);
        order.setOrderBook(orderBody.getOrderBook());
        order.setPrice(orderBody.getPrice());

        for (OrderBook orderBook : orderBody.getOrderBook()) {
            OrderBookId orderBookId = new OrderBookId();
            orderBookId.setBookId(orderBook.getBook().getId());
            orderBookId.setBookId(order.getId());
            orderBook.setId(orderBookId);
            orderBook.setOrder(order);
            orderBookRepository.save(orderBook);
        }

        if (order.getTypeOfPacking() != null) {
            order.setDedication(orderBody.getDedication());
            order.createPacking(order.getTypeOfPacking());
            packingRepository.save(order.getPacking());
        }

        return new ResponseEntity<>(orderRepository.save(order), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateOrder(@RequestBody Order order, @PathVariable Long id) {
        Optional<User> user = userRepository.findById(order.getUser().getId());
        user.ifPresent(order::setUser);
        orderRepository.findById(id).map(oldOrder -> {
            BeanUtils.copyProperties(order, oldOrder, new String[] { "id" });
            return orderRepository.save(oldOrder);
        }).orElseGet(() -> orderRepository.save(order));

        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderRepository.delete(getOrder(id).getBody());
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
}
