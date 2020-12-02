package com.sbd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sbd.bookstore.repository.*;
import com.sbd.model.*;

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

    @Override
    public void run(String... args) throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User("Natalia", "Tarasiuk", "natalia.tarasiuk@123.pl", "qweasdzxc", "123456789", "...", "12-345", "xyz"));
        users.add(new User("Michał", "Sawicki", "michał.sawicki@123.pl", "qweasdzxc", "123456789", "...", "12-345", "xyz"));
        users.add(new User("Ida", "Milewska", "ida.milewska@123.pl", "qweasdzxc", "123456789", "...", "12-345", "xyz"));

        List<Publisher> publishers = new ArrayList<>();
        publishers.add(new Publisher("Polskie Wydawnictwo"));
        publishers.add(new Publisher("Zagraniczne Wydawnictwo"));

        List<Book> books = new ArrayList<>();
        books.add(new Book("Harry Potter", BigDecimal.valueOf(39.99), 17));
        books.add(new Book("Pan Tadeusz", BigDecimal.valueOf(50.99), 20));
        books.add(new Book("Dziady cz.3", BigDecimal.valueOf(24.99), 9));

        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Polskie"));
        categories.add(new Category("Zagraniczne"));

        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Adam", "Mickiewicz"));
        authors.add(new Author("J.K.", "Rowling"));

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

    }
}
