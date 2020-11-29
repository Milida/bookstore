package com.sbd.controller;

import com.sbd.bookstore.repository.CategoryRepository;
import com.sbd.model.Category;
import com.sbd.payroll.ConflictException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Category> addCategory(@RequestBody Category category) {
        if (categoryRepository.existsByName(category.getName()))
            throw new ConflictException(String.format("Category with name '%s' already exists", category.getName()));

        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.CREATED);
    }
}
