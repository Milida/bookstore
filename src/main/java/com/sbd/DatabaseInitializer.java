package com.sbd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbd.bookstore.repository.*;
import com.sbd.model.*;
import com.sbd.model.embedded.OrderBookId;
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
    @Autowired
    CartRepository cartRepository;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User("Natalia", "Tarasiuk", "natalia.tarasiuk@123.pl", "qweasdzxc", "123456789", "...", "12-345", "xyz"));
        users.add(new User("Michał", "Sawicki", "msawicki@email.com", "qwerty", "123456789", "...", "12-345", "xyz"));
        users.add(new User("Ida", "Milewska", "ida.milewska@123.pl", "qweasdzxc", "123456789", "...", "12-345", "xyz"));
        users.add(new User("Natka", "Kot", "natka@wp.pl", "natka", "123456789", "Malinowa 1", "15-455", "Bajkolandia"));

        Employee e = new Employee();
        e.setUser(users.get(1));
        employeeRepository.save(e);

        List<Publisher> publishers = new ArrayList<>();
        publishers.add(new Publisher("Polskie Wydawnictwo"));
        publishers.add(new Publisher("Zagraniczne Wydawnictwo"));
        publishers.add(new Publisher("Addison–Wesley"));
        publishers.add(new Publisher("Macmillan"));


        List<Book> books = new ArrayList<>();
        books.add(new Book("Harry Potter", BigDecimal.valueOf(39.99), 17));
        books.add(new Book("Pan Tadeusz", BigDecimal.valueOf(50.99), 20));
        books.add(new Book("Dziady cz.3", BigDecimal.valueOf(24.99), 9));
        books.add(new Book("Ballads and Romances", BigDecimal.valueOf(29.99),70));
        books.add(new Book("Alice's Adventures in Wonderland ", BigDecimal.valueOf(49.99), 21));
        books.add(new Book("The C++ Programming Language", BigDecimal.valueOf(79.99), 6));
        books.get(0).setDescription("Harry Potter is a series of seven fantasy novels written by British author J. K. Rowling. The novels chronicle the lives of a young wizard, Harry Potter, and his friends Hermione Granger and Ron Weasley, all of whom are students at Hogwarts School of Witchcraft and Wizardry. The main story arc concerns Harry's struggle against Lord Voldemort, a dark wizard who intends to become immortal, overthrow the wizard governing body known as the Ministry of Magic and subjugate all wizards and Muggles (non-magical people).");
        books.get(1).setDescription(" Pan Tadeusz is an epic poem by the Polish poet, writer, translator and philosopher Adam Mickiewicz. The book, written in Polish alexandrines, was first published on 28 June 1834 in Paris. It is deemed the last great epic poem in European literature.");
        books.get(2).setDescription("This part is thought to be the most significant one, or even one of the finest poems in the Polish literature. The main character bears a resemblance to Gustaw from the IV part, but he is no longer a 'romantic lover'. The drama was written after the failure of the November Insurrection");
        books.get(3).setDescription("The collection of ballads written by Polish Romantic-era poet Adam Mickiewicz in 1822 and first published in Vilnius, Russian Empire, as part of the first volume of his Poezje ('Poetry').");
        books.get(4).setDescription("One of the best-known and most popular works of English-language fiction, its narrative, structure, characters and imagery have been enormously influential in popular culture and literature, especially in the fantasy genre.");
        books.get(5).setDescription("The C++ Programming Language is a computer programming book first published in October 1985. It was the first book to describe the C++ programming language, written by the language's creator, Bjarne Stroustrup. In the absence of an official standard, the book served for several years as the de facto documentation for the evolving C++ language");

        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Polskie"));
        categories.add(new Category("Zagraniczne"));
        categories.add(new Category("Fantasy"));
        categories.add(new Category("Naukowe"));
        categories.add(new Category("Historyczne"));
        categories.get(0).setDescription("Ksiązki pisane przez polskich autorów");
        categories.get(1).setDescription("Ksiązki pisane przez zagranicznych autorów");
        categories.get(2).setDescription("Przedstawiają nierealny świat.");
        categories.get(3).setDescription("Książki przeznaczone do poszerzania wiedzy naukowej");
        categories.get(4).setDescription("Ksiązki o tematyce historycznej");

        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Adam", "Mickiewicz"));
        authors.add(new Author("J.K.", "Rowling"));
        authors.add(new Author("Lewis", "Carroll"));
        authors.add(new Author("Bjarne", "Stroustrup"));


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
        payments.add(new Payment("Pobranie"));

        List<Shipment> shipments = new ArrayList<>();
        shipments.add(new Shipment());
        shipments.get(0).setName("Inpost");
        shipments.get(0).setPrice(BigDecimal.valueOf(12.99));
        shipments.add(new Shipment());
        shipments.get(1).setName("Poczta Polska");
        shipments.get(1).setPrice(BigDecimal.valueOf(18.20));
        shipments.add(new Shipment());
        shipments.get(2).setName("Kurier DPD Pobranie");
        shipments.get(2).setPrice(BigDecimal.valueOf(22.90));
        shipments.add(new Shipment());
        shipments.get(3).setName("Kurier DPD");
        shipments.get(3).setPrice(BigDecimal.valueOf(17.90));

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

        // CartId cartId = new CartId();
        // cartId.setBookId(books.get(0).getId());
        // cartId.setUserId(users.get(0).getId());

        // Cart cart = new Cart();
        // cart.setId(cartId);
        // cart.setBook(books.get(0));
        // cart.setUser(users.get(0));
        // cart.setQuantity(5);
        // cartRepository.save(cart);


        books.get(0).addCategory(categories.get(1));
        books.get(1).addCategory(categories.get(0));
        books.get(2).addCategory(categories.get(0));
        books.get(3).addCategory(categories.get(0));
        books.get(4).addCategory(categories.get(2));
        //books.get(1).addCategory(categories.get(2));
        books.get(5).addCategory(categories.get(3));


        books.get(0).addAuthor(authors.get(1));
        books.get(1).addAuthor(authors.get(0));
        books.get(2).addAuthor(authors.get(0));
        books.get(3).addAuthor(authors.get(0));
        books.get(4).addAuthor(authors.get(2));
        books.get(5).addAuthor(authors.get(3));

        publishers.get(1).addBook(books.get(0));
        publishers.get(0).addBook(books.get(1));
        publishers.get(0).addBook(books.get(2));
        publishers.get(0).addBook(books.get(3));
        publishers.get(3).addBook(books.get(4));
        publishers.get(2).addBook(books.get(5));


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
