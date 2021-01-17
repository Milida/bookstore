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
@SuppressWarnings("deprecation")
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
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BookstoreRepository bookstoreRepository;
    @Autowired
    RateRepository rateRepository;
    @Autowired
    DecoratorRepository decoratorRepository;

    @Override
    public void run(String... args) {
        Role student = new Role("Student");
        Role employee = new Role("Employee");
        Role company = new Role("Company");
        Role regular = new Role("Regular");
        roleRepository.save(student);
        roleRepository.save(employee);
        roleRepository.save(company);
        roleRepository.save(regular);

        List<User> users = new ArrayList<>();
        users.add(new User("Natalia", "Tarasiuk", "natalia.tarasiuk@123.pl", "qweasdzxc", "123456789", "...", "12-345", "xyz", company));
        users.add(new User("Michał", "Sawicki", "msawicki@email.com", "qwerty", "123456789", "...", "12-345", "xyz", employee));
        users.add(new User("Ida", "Milewska", "ida.milewska@123.pl", "qweasdzxc", "123456789", "...", "12-345", "xyz", student));
        users.add(new User("Natka", "Kot", "natka@wp.pl", "natka", "123456789", "Malinowa 1", "15-455", "Bajkolandia", regular));

        Employee e = new Employee();
        e.setUser(users.get(1));
        employeeRepository.save(e);

        List<Publisher> publishers = new ArrayList<>();
        publishers.add(new Publisher("Polskie Wydawnictwo"));
        publishers.add(new Publisher("Zagraniczne Wydawnictwo"));
        publishers.add(new Publisher("Addison–Wesley"));
        publishers.add(new Publisher("Macmillan"));
        publishers.add(new Publisher("The Russian Messenger "));


        List<Book> books = new ArrayList<>();
        books.add(new Book("Harry Potter", BigDecimal.valueOf(39.99), 17));
        books.add(new Book("Pan Tadeusz", BigDecimal.valueOf(50.99), 20));
        books.add(new Book("Dziady cz.3", BigDecimal.valueOf(24.99), 9));
        books.add(new Book("Ballads and Romances", BigDecimal.valueOf(29.99),70));
        books.add(new Book("Alice's Adventures in Wonderland ", BigDecimal.valueOf(49.99), 21));
        books.add(new Book("The C++ Programming Language", BigDecimal.valueOf(79.99), 6));
        books.add(new Book("Krzyżacy", BigDecimal.valueOf(39.99), 15));
        books.add(new Book("Balladyna", BigDecimal.valueOf(19.99), 15));
        books.add(new Book("Zbrodnia i Kara", BigDecimal.valueOf(89.99), 10));
        books.add(new Book("Wojna i Pokój", BigDecimal.valueOf(44.99), 20));
        books.add(new Book("SQL w mgnieniu oka", BigDecimal.valueOf(24.99), 20));
        books.add(new Book("Szybsza Sieć z językami PHP, MySQL i JavaScript.", BigDecimal.valueOf(29.99), 25));
        books.add(new Book("Latarnik", BigDecimal.valueOf(14.99), 20));
        books.add(new Book("Potop", BigDecimal.valueOf(42.99), 20));
        books.add(new Book("W Pustyni i w Puszczy", BigDecimal.valueOf(12.99), 20));
        books.add(new Book("Ogniem i mieczem", BigDecimal.valueOf(47.99), 20));
        books.add(new Book("Kordian", BigDecimal.valueOf(13.99), 20));
        books.add(new Book("Powrót taty", BigDecimal.valueOf(19.99), 20));

        books.get(0).setDescription("Harry Potter is a series of seven fantasy novels written by British author J. K. Rowling. The novels chronicle the lives of a young wizard, Harry Potter, and his friends Hermione Granger and Ron Weasley, all of whom are students at Hogwarts School of Witchcraft and Wizardry. The main story arc concerns Harry's struggle against Lord Voldemort, a dark wizard who intends to become immortal, overthrow the wizard governing body known as the Ministry of Magic and subjugate all wizards and Muggles (non-magical people).");
        books.get(1).setDescription(" Pan Tadeusz is an epic poem by the Polish poet, writer, translator and philosopher Adam Mickiewicz. The book, written in Polish alexandrines, was first published on 28 June 1834 in Paris. It is deemed the last great epic poem in European literature.");
        books.get(2).setDescription("This part is thought to be the most significant one, or even one of the finest poems in the Polish literature. The main character bears a resemblance to Gustaw from the IV part, but he is no longer a 'romantic lover'. The drama was written after the failure of the November Insurrection");
        books.get(3).setDescription("The collection of ballads written by Polish Romantic-era poet Adam Mickiewicz in 1822 and first published in Vilnius, Russian Empire, as part of the first volume of his Poezje ('Poetry').");
        books.get(4).setDescription("One of the best-known and most popular works of English-language fiction, its narrative, structure, characters and imagery have been enormously influential in popular culture and literature, especially in the fantasy genre.");
        books.get(5).setDescription("The C++ Programming Language is a computer programming book first published in October 1985. It was the first book to describe the C++ programming language, written by the language's creator, Bjarne Stroustrup. In the absence of an official standard, the book served for several years as the de facto documentation for the evolving C++ language");
        books.get(6).setDescription("This is a 1900 historical novel written by the Polish Positivist writer and the 1905 Nobel laureate, Henryk Sienkiewicz.");
        books.get(7).setDescription(" The story revolves around the rise and fall of Balladyna, a fictional Slavic queen.");
        books.get(8).setDescription("Crime and Punishment focuses on the mental anguish and moral dilemmas of Rodion Raskolnikov, an impoverished ex-student in Saint Petersburg who formulates a plan to kill an unscrupulous pawnbroker for her money.");
        books.get(9).setDescription("The novel chronicles the French invasion of Russia and the impact of the Napoleonic era on Tsarist society through the stories of five Russian aristocratic families.");
        books.get(10).setDescription("SQL is a language to manipulate data found in selling data. Its knowledge of the test will get you instantly from the base of interest to you information. It is the first to subject them to an advanced procedure or to draw conclusions.");
        books.get(11).setDescription("In this book, you will learn how to improve the performance of any web application to meet the Faster Web criteria. You will soon start working with the latest performance measurement, profiling, and monitoring tools for PHP, MySQL and JavaScript.");
        books.get(12).setDescription("Skawiński is already an older man. For many years she wandered around the world. His dream is to finally find a place where he will find the final haven, peace and rest after the hardships of life. When he gets a job at the off-the-beaten-track lighthouse, he thinks he has finally found what he was looking for.");
        books.get(13).setDescription("In 1655, a huge Swedish army enters the borders of the Polish-Lithuanian Commonwealth. Poles will remember this invasion as the 'Swedish Deluge'. Treason multiplies - the nobility of the mass movement near Ujście succumbs, Janusz Radziwiłł surrenders Lithuania to the Swedes. Częstochowa becomes the mainstay of Poles, in defense of which Andrzej Kmicic will play a significant role.");
        books.get(14).setDescription("A fascinating travel and adventure novel about extraordinary friendship, dedication, devotion and great courage. Staś Tarkowski and Nel Rawlinson live in Port Said. A fourteen-year-old boy already considers himself an adult man, while a seven-year-old girl prefers her father to be nearby.");
        books.get(15).setDescription("The Cossack uprising under the command of Chmielnicki breaks out. A haughty leader wins more victories. Jan Skrzetuski is following with concern the development of events. He is very concerned about the fate of Helena Kurcewiczówna, his beloved, but he puts patriotic duty ahead of personal happiness. However, he has faithful friends - Zagłoba, Wołodyjowski and Rzędzian will try to free Helena from the hands of the dangerous Bohun.");
        books.get(16).setDescription("A drama that is a polemic with historiosophical projects included in the third part of 'Dziady'. It shows the fate of a sensitive young man who tries to find an idea that will allow him to direct his life, and when they find it in the fight for the freedom of their homeland, he falls victim to his sensitivity and compulsions in history ruled by evil forces.");
        books.get(17).setDescription("A beautifully illustrated ballad by Adam Mickiewicz. Large format, hardcover, elegant color edition - these are the main advantages of this item. This publication teaches respect for parents and faith in the power of prayer. We recommend it especially to children who, thanks to beautiful 'Disney' illustrations, can move into the described world of fantasy. Classic always good!");

        Bookstore bookstore = Bookstore.getInstance();

        bookstore.setName("Best Bookstore");
        bookstore.bookRepository = bookRepository;
        bookstore.setBooks(books);
        bookstoreRepository.save(bookstore);
        
        for (Book book : books) {
            book.setBookstore(bookstore);
        }

        Rate rate = new Rate("Euro", "€", BigDecimal.valueOf(0.22));
        rate.addObserver(bookstore);
        rate.setRate(BigDecimal.valueOf(0.22));
        rateRepository.save(rate);


        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Polskie"));
        categories.add(new Category("Zagraniczne"));
        categories.add(new Category("Fantasy"));
        categories.add(new Category("Naukowe"));
        categories.add(new Category("Historyczne"));
        categories.add(new Category("Dramat"));
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
        authors.add(new Author("Lew", "Tołstoj"));
        authors.add(new Author("Henryk", "Sienkiewicz"));
        authors.add(new Author("Juliusz", "Słowacki"));
        authors.add(new Author("Jan", "Brzechwa"));
        authors.add(new Author("Eliza", "Orzeszkowa"));
        authors.add(new Author("Fiodor", "Dostojewski"));
        authors.add(new Author("Ben", "Forta"));
        authors.add(new Author("Andrew", "Caya"));



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



        books.get(0).addCategory(categories.get(1));
        books.get(1).addCategory(categories.get(0));
        books.get(2).addCategory(categories.get(0));
        books.get(3).addCategory(categories.get(0));
        books.get(4).addCategory(categories.get(2));
        //books.get(1).addCategory(categories.get(2));
        books.get(5).addCategory(categories.get(3));
        books.get(6).addCategory(categories.get(4));
        books.get(7).addCategory(categories.get(3));
        books.get(8).addCategory(categories.get(5));
        books.get(9).addCategory(categories.get(5));
        books.get(10).addCategory(categories.get(3));
        books.get(11).addCategory(categories.get(3));
        books.get(12).addCategory(categories.get(0));
        books.get(13).addCategory(categories.get(0));
        books.get(14).addCategory(categories.get(0));
        books.get(15).addCategory(categories.get(0));
        books.get(16).addCategory(categories.get(0));
        books.get(16).addCategory(categories.get(5));
        books.get(17).addCategory(categories.get(0));
        books.get(13).addCategory(categories.get(2));
        books.get(14).addCategory(categories.get(3));
        books.get(15).addCategory(categories.get(2));
        books.get(17).addCategory(categories.get(5));


        books.get(0).addAuthor(authors.get(1));
        books.get(1).addAuthor(authors.get(0));
        books.get(2).addAuthor(authors.get(0));
        books.get(3).addAuthor(authors.get(0));
        books.get(4).addAuthor(authors.get(2));
        books.get(5).addAuthor(authors.get(3));
        books.get(6).addAuthor(authors.get(5));
        books.get(7).addAuthor(authors.get(6));
        books.get(8).addAuthor(authors.get(9));
        books.get(9).addAuthor(authors.get(4));
        books.get(10).addAuthor(authors.get(10));
        books.get(11).addAuthor(authors.get(11));
        books.get(12).addAuthor(authors.get(5));
        books.get(13).addAuthor(authors.get(5));
        books.get(14).addAuthor(authors.get(5));
        books.get(15).addAuthor(authors.get(5));
        books.get(16).addAuthor(authors.get(0));
        books.get(17).addAuthor(authors.get(0));

        publishers.get(1).addBook(books.get(0));
        publishers.get(0).addBook(books.get(1));
        publishers.get(0).addBook(books.get(2));
        publishers.get(0).addBook(books.get(3));
        publishers.get(3).addBook(books.get(4));
        publishers.get(2).addBook(books.get(5));
        publishers.get(0).addBook(books.get(6));
        publishers.get(0).addBook(books.get(7));
        publishers.get(4).addBook(books.get(8));
        publishers.get(4).addBook(books.get(9));
        publishers.get(0).addBook(books.get(10));
        publishers.get(0).addBook(books.get(11));
        publishers.get(0).addBook(books.get(12));
        publishers.get(0).addBook(books.get(13));
        publishers.get(0).addBook(books.get(14));
        publishers.get(0).addBook(books.get(15));
        publishers.get(0).addBook(books.get(16));
        publishers.get(0).addBook(books.get(17));

        
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
            bookRepository.saveAndFlush(book);
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

        Decorator decorator1 = new Decorator("Hard cover");
        Decorator decorator2 = new Decorator("Additional cover");
        Decorator decorator3 = new Decorator("Big Format");
        Decorator decorator4 = new Decorator("CD Book");
        decoratorRepository.save(decorator1);
        decoratorRepository.save(decorator2);
        decoratorRepository.save(decorator3);
        decoratorRepository.save(decorator4);

        orderRepository.save(order);

        for (OrderBook orderBook: orderBooks) {
            orderBook.setOrder(order);
            orderBookRepository.save(orderBook);
        }
    }
}
