package com.sbd.controller;

import com.sbd.bookstore.repository.CategoryRepository;
import com.sbd.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping()
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(path="/category/add/{name}/{description}")
    public @ResponseBody
    ResponseEntity<List<Category>> addNewCategory(@PathVariable String name, @PathVariable String description) {
        Category n = new Category();
        n.setName(name);
        n.setDescription(description);
        categoryRepository.save(n);
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.CREATED);
    }

    @GetMapping(path="/categories")
    public @ResponseBody
    ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }
}
