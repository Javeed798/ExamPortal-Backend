package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category category1 = this.categoryService.addCategory(category);
        return ResponseEntity.ok(category1);
    }

//    get category

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id){
        Category category = this.categoryService.getCategory(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/")
    public ResponseEntity<Set<Category>> getAllCategories(){
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

//    update api
    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category){
        return this.categoryService.updateCategory(category);
    }

//    delete

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        this.categoryService.deleteCategory(id);
    }

}
