package org.example.controller;

import org.example.entity.Category;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.list();
    }

    @PostMapping
    public boolean addCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @PutMapping("/{id}")
    public boolean updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        category.setCategoryId(id);
        return categoryService.updateById(category);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable Integer id) {
        return categoryService.removeById(id);
    }
}
