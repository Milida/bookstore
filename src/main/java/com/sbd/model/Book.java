package com.sbd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;    
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity(name = "books")
public class Book implements BaseBook{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 127, nullable = false)
    private String title;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private BigDecimal priceEur;

    @Column(nullable = false)
    private Integer quantity;

    @Column(length = 1023)
    private String description;

    @JsonIgnoreProperties({"books", "hibernateLazyInitializer"})
    @ManyToOne(cascade = { CascadeType.MERGE})
    private Publisher publisher;

    @JsonIgnoreProperties({"books", "hibernateLazyInitializer"})
    @ManyToOne(cascade = { CascadeType.MERGE})
    private Bookstore bookstore;

    @JsonIgnoreProperties("books")
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "categoriesBooks", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    @JsonIgnoreProperties("books")
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "authorsBooks", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors = new ArrayList<>();

    public Book(String title, BigDecimal price, Integer quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getPriceEur() {
        return priceEur;
    }
    

    // public String getFeaturesDescription() {
    //     return featureDescription;
    // }

    public Integer getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Bookstore getBookstore() {
        return bookstore;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public void setPriceEur(BigDecimal priceEur) {
        this.priceEur = priceEur;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addCategory(Category category) {
        categories.add(category);
        category.getBooks().add(this);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.getBooks().remove(this);
    }

    public void addAuthor(Author author) {
        authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
        author.getBooks().remove(this);
    }

    public void setBookstore(Bookstore bookstore) {
        this.bookstore = bookstore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Book))
            return false;
        return id != null && id.equals(((Book) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
