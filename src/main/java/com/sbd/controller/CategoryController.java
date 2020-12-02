package com.sbd.controller;

import com.sbd.bookstore.repository.CategoryRepository;
import com.sbd.model.Book;
import com.sbd.model.Category;
import com.sbd.payroll.ConflictException;
import com.sbd.payroll.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<List<Book>> getCategoryBooks(@PathVariable Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category with ID = %d was not found", id)));
        return new ResponseEntity<>(category.getBooks(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Category> addCategory(@RequestBody Category category) {
        if (categoryRepository.existsByName(category.getName()))
            throw new ConflictException(String.format("Category with name '%s' already exists", category.getName()));

        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.CREATED);
    }
}
