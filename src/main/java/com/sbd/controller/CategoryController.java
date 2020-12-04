package com.sbd.controller;

import com.sbd.bookstore.repository.CategoryRepository;
import com.sbd.model.Category;
import com.sbd.payroll.ConflictException;
import com.sbd.payroll.NotFoundException;

import org.springframework.beans.BeanUtils;
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
    ResponseEntity<Category> getCategory(@PathVariable Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category with ID = %d was not found", id)));
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Category> addCategory(@RequestBody Category category) {
        if (categoryRepository.existsByName(category.getName()))
            throw new ConflictException(String.format("Category with name '%s' already exists", category.getName()));

        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateBook(@RequestBody Category category, @PathVariable Long id) {

        categoryRepository.findById(id).map(oldCategory -> {
            BeanUtils.copyProperties(category, oldCategory, new String[] { "id" });
            return categoryRepository.save(oldCategory);
        }).orElseGet(() -> {
            return categoryRepository.save(category);
        });

        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> removeCategory(@PathVariable Long id) {
        Category category = getCategory(id).getBody();
        if(category.getBooks().isEmpty())
        {
            categoryRepository.delete(getCategory(id).getBody());
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        } else {
            throw new ConflictException("Cannot remove category with assigned books!");
        }
    }
}
