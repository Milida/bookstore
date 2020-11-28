package com.sbd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sbd.bookstore.repository.AuthorRepository;
import com.sbd.bookstore.repository.BookRepository;
import com.sbd.bookstore.repository.CategoryRepository;
import com.sbd.bookstore.repository.PublisherRepository;
import com.sbd.model.Book;
import com.sbd.model.Publisher;
import com.sbd.model.Category;
import com.sbd.model.Author;

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

    @Override
    public void run(String... args) throws Exception {

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

    }
}
