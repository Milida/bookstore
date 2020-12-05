package com.sbd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbd.bookstore.repository.*;
import com.sbd.model.*;

import com.sbd.model.embedded.OrderBookId;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    PublisherRepository publisherRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderStatusRepository orderStatusRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    ShipmentRepository shipmentRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderBookRepository orderBookRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User("Natalia", "Tarasiuk", "natalia.tarasiuk@123.pl", "qweasdzxc", "123456789", "...", "12-345", "xyz"));
        users.add(new User("Michał", "Sawicki", "msawicki@email.com", "qwerty", "123456789", "...", "12-345", "xyz"));
        users.add(new User("Ida", "Milewska", "ida.milewska@123.pl", "qweasdzxc", "123456789", "...", "12-345", "xyz"));

        Employee e = new Employee();
        e.setUser(users.get(1));
        employeeRepository.save(e);

        List<Publisher> publishers = new ArrayList<>();
        publishers.add(new Publisher("Polskie Wydawnictwo"));
        publishers.add(new Publisher("Zagraniczne Wydawnictwo"));

        List<Book> books = new ArrayList<>();
        books.add(new Book("Harry Potter", BigDecimal.valueOf(39.99), 17));
        books.add(new Book("Pan Tadeusz", BigDecimal.valueOf(50.99), 20));
        books.add(new Book("Dziady cz.3", BigDecimal.valueOf(24.99), 9));
        books.get(0).setDescription("Harry Potter is a series of seven fantasy novels written by British author J. K. Rowling. The novels chronicle the lives of a young wizard, Harry Potter, and his friends Hermione Granger and Ron Weasley, all of whom are students at Hogwarts School of Witchcraft and Wizardry. The main story arc concerns Harry's struggle against Lord Voldemort, a dark wizard who intends to become immortal, overthrow the wizard governing body known as the Ministry of Magic and subjugate all wizards and Muggles (non-magical people).");
        
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Polskie"));
        categories.add(new Category("Zagraniczne"));

        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Adam", "Mickiewicz"));
        authors.add(new Author("J.K.", "Rowling"));

        List<OrderStatus> orderStatuses = new ArrayList<>();
        orderStatuses.add(new OrderStatus());
        orderStatuses.get(0).setName("Nowe");
        orderStatuses.add(new OrderStatus());
        orderStatuses.get(1).setName("Opłacone");
        orderStatuses.add(new OrderStatus());
        orderStatuses.get(2).setName("Wysłane");

        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment("PayU"));
        payments.add(new Payment("Przelew"));

        List<Shipment> shipments = new ArrayList<>();
        shipments.add(new Shipment());
        shipments.get(0).setName("Inpost");
        shipments.get(0).setPrice(BigDecimal.valueOf(12.99));
        shipments.add(new Shipment());
        shipments.get(1).setName("Poczta Polska");
        shipments.get(1).setPrice(BigDecimal.valueOf(18.20));

        List<OrderBook> orderBooks = new ArrayList<>();
        orderBooks.add(new OrderBook(books.get(0), 20, BigDecimal.valueOf(13.99)));
        orderBooks.add(new OrderBook(books.get(2), 15, BigDecimal.valueOf(99.90)));

        Order order = new Order(users.get(0), orderStatuses.get(1), payments.get(0), shipments.get(1),BigDecimal.valueOf(120.13), new Date());
        order.setOrderBook(orderBooks);

        List<OrderBookId> orderBookIds = new ArrayList<>();
        orderBookIds.add(new OrderBookId());
        orderBookIds.get(0).setBookId(orderBooks.get(0).getBook().getId());
        orderBookIds.get(0).setOrderId(order.getId());
        orderBookIds.add(new OrderBookId());
        orderBookIds.get(1).setBookId(orderBooks.get(1).getBook().getId());
        orderBookIds.get(1).setOrderId(order.getId());
        orderBooks.get(0).setId(orderBookIds.get(0));
        orderBooks.get(1).setId(orderBookIds.get(1));


        books.get(0).addCategory(categories.get(0));
        books.get(1).addCategory(categories.get(1));
        books.get(2).addCategory(categories.get(1));

        books.get(0).addAuthor(authors.get(0));
        books.get(1).addAuthor(authors.get(1));
        books.get(2).addAuthor(authors.get(1));

        publishers.get(0).addBook(books.get(1));
        publishers.get(0).addBook(books.get(2));
        publishers.get(1).addBook(books.get(0));

        for (Category category : categories) {
            categoryRepository.save(category);
        }

        for (Author author : authors) {
            authorRepository.save(author);
        }

        for (Publisher publisher : publishers) {
            publisherRepository.save(publisher);
        }

        for (Book book : books) {
            bookRepository.save(book);
        }

        for (User user : users) {
            userRepository.save(user);
        }

        for (Payment payment: payments) {
            paymentRepository.save(payment);
        }

        for (OrderStatus status: orderStatuses) {
            orderStatusRepository.save(status);
        }

        for (Shipment shipment: shipments) {
            shipmentRepository.save(shipment);
        }

        orderRepository.save(order);

        for (OrderBook orderBook: orderBooks) {
            orderBook.setOrder(order);
            orderBookRepository.save(orderBook);
        }
    }
}
